package com.tengwar.translator.domain

import com.tengwar.translator.tengwar.TengwarTransliterator

/** Result of a full two-hop translation. */
data class TranslationResult(
    val direction: Direction,
    /** Original user input. */
    val source: String,
    /** Intermediate English text. */
    val english: String,
    /** Final text: Sindarin (latin) for [Direction.RU_TO_SINDARIN], Russian otherwise. */
    val target: String,
    /** [target] written in Tengwar (CSUR codepoints mixed with separators). */
    val tengwar: String,
    /** Words that were not found in the offline lexicon. */
    val unknown: List<String>
)

/**
 * Two-hop offline translator.
 *
 * Russian -> Sindarin:  RU -[ruEn]-> EN -[enSin]-> SINDARIN
 * Sindarin -> Russian:  SINDARIN -[sinEn]-> EN -[enRu]-> RU
 */
class Translator(private val lexicon: Lexicon = Lexicon) {

    fun translate(input: String, direction: Direction): TranslationResult {
        val unknown = LinkedHashSet<String>()

        return when (direction) {
            Direction.RU_TO_SINDARIN -> {
                val english = translateLine(input, lexicon.ruEn, unknown)
                val sindarin = translateLine(english, lexicon.enSin, unknown)
                TranslationResult(
                    direction = direction,
                    source = input,
                    english = english,
                    target = sindarin,
                    tengwar = TengwarTransliterator.transliterate(sindarin),
                    unknown = unknown.toList()
                )
            }

            Direction.SINDARIN_TO_RUSSIAN -> {
                val english = translateLine(input, lexicon.sinEn, unknown)
                val russian = translateLine(english, lexicon.enRu, unknown)
                TranslationResult(
                    direction = direction,
                    source = input,
                    english = english,
                    target = russian,
                    // The tengwar line always shows the Sindarin side of the translation.
                    tengwar = TengwarTransliterator.transliterate(input),
                    unknown = unknown.toList()
                )
            }
        }
    }

    /**
     * Translates a line token by token, preserving spaces and punctuation. Multi-word
     * phrases found in [dict] are matched greedily (longest first).
     */
    private fun translateLine(
        input: String,
        dict: Map<String, String>,
        unknown: MutableSet<String>
    ): String {
        val chunks = splitChunks(input)
        val out = StringBuilder()

        var i = 0
        while (i < chunks.size) {
            val chunk = chunks[i]
            if (!chunk.isWord) {
                out.append(chunk.text)
                i++
                continue
            }

            val match = matchPhrase(chunks, i, dict)
            if (match != null) {
                out.append(match.translation)
                i += match.length
                continue
            }

            val word = chunk.text
            val translated = lookup(word, dict)
            if (translated != null) {
                out.append(translated)
            } else {
                out.append(word)
                unknown.add(word)
            }
            i++
        }
        return out.toString()
    }

    private data class PhraseMatch(val translation: String, val length: Int)

    /**
     * Tries to match 2..maxPhraseWords consecutive word chunks (separated by a single
     * space) against phrase keys of [dict].
     */
    private fun matchPhrase(
        chunks: List<Chunk>,
        start: Int,
        dict: Map<String, String>
    ): PhraseMatch? {
        val words = ArrayList<String>()
        words.add(chunks[start].text)
        var lastWordIndex = start

        while (words.size < lexicon.maxPhraseWords) {
            val spaceIndex = lastWordIndex + 1
            val wordIndex = lastWordIndex + 2
            if (wordIndex >= chunks.size) break
            if (chunks[spaceIndex].isWord || chunks[spaceIndex].text != " ") break
            if (!chunks[wordIndex].isWord) break

            words.add(chunks[wordIndex].text)
            val translation = dict[words.joinToString(" ").lowercase()]
            if (translation != null) {
                return PhraseMatch(translation, wordIndex - start + 1)
            }
            lastWordIndex = wordIndex
        }
        return null
    }

    /** Looks a word up, with simple morphological fallbacks. */
    private fun lookup(word: String, dict: Map<String, String>): String? {
        val lower = word.lowercase()
        dict[lower]?.let { return it }

        for (candidate in candidates(lower)) {
            dict[candidate]?.let { return it }
        }
        return null
    }

    private fun candidates(word: String): Sequence<String> = sequence {
        val endings = when {
            word.any { it in "аеёиоуыэюя" } -> RUSSIAN_ENDINGS
            else -> ENGLISH_ENDINGS + SINDARIN_ENDINGS
        }
        for (ending in endings) {
            if (word.length > ending.length + 1 && word.endsWith(ending)) {
                yield(word.dropLast(ending.length))
            }
        }
    }

    private data class Chunk(val text: String, val isWord: Boolean)

    private fun splitChunks(input: String): List<Chunk> {
        val chunks = ArrayList<Chunk>()
        val current = StringBuilder()
        var currentIsWord = false

        fun flush() {
            if (current.isNotEmpty()) {
                chunks.add(Chunk(current.toString(), currentIsWord))
                current.setLength(0)
            }
        }

        for (c in input) {
            val isWord = c.isLetter() || c == '\'' || c == '’'
            if (current.isEmpty()) {
                currentIsWord = isWord
                current.append(c)
            } else if (isWord == currentIsWord) {
                current.append(c)
            } else {
                flush()
                currentIsWord = isWord
                current.append(c)
            }
        }
        flush()
        return chunks
    }

    private companion object {
        val RUSSIAN_ENDINGS = listOf(
            "ами", "ями", "ого", "его", "ому", "ему", "ыми", "ими",
            "ов", "ев", "ей", "ам", "ям", "ах", "ях", "ой", "ый", "ий",
            "ая", "яя", "ое", "ее", "ые", "ие",
            "а", "я", "о", "е", "у", "ю", "ы", "и", "ь"
        )
        val ENGLISH_ENDINGS = listOf("s", "es", "ed", "ing", "ly")
        val SINDARIN_ENDINGS = listOf("in", "ath", "rim", "ir", "en")
    }
}
