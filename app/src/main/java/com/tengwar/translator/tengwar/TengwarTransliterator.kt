package com.tengwar.translator.tengwar

/**
 * Transliterates Sindarin (or any Latin-script Elvish text) into Tengwar.
 *
 * The mode implemented here is the "General Use" ómatehtar mode: consonant sounds are
 * written with tengwar and vowels are written as tehtar diacritics placed above the
 * *preceding* consonant tengwa. A vowel that has no preceding consonant is carried by a
 * short carrier (or by yanta / úrë for the second element of a diphthong). Doubled
 * consonants are marked with the doubler.
 *
 * The result string mixes Tengwar codepoints (CSUR private use area, rendered with the
 * bundled "Alcarin Tengwar" font) with plain separators (spaces, punctuation), which are
 * rendered with the regular text font.
 */
object TengwarTransliterator {

    private val VOWELS = setOf('a', 'e', 'i', 'o', 'u', 'y')

    /** Two-letter Sindarin digraphs that represent a single sound. */
    private val DIGRAPHS: Map<String, Char> = mapOf(
        "th" to Tengwar.THUULE,
        "dh" to Tengwar.ANTO,
        "ch" to Tengwar.HWESTA,
        "sh" to Tengwar.HARMA,
        "ph" to Tengwar.FORMEN,
        "rh" to Tengwar.ARDA,
        "lh" to Tengwar.ALDA,
        "ng" to Tengwar.NOLDO,
        "hw" to Tengwar.HWESTA_SINDARINWA
    )

    /** Diphthongs: first element is written as a tehta over the carrier of the second. */
    private val DIPHTHONGS = setOf("ai", "ei", "ui", "ae", "oe", "au", "aw", "oi")

    private val SINGLE_CONSONANTS: Map<Char, Char> = mapOf(
        't' to Tengwar.TINCO,
        'p' to Tengwar.PARMA,
        'c' to Tengwar.QUESSE,
        'k' to Tengwar.QUESSE,
        'q' to Tengwar.QUESSE,
        'd' to Tengwar.ANDO,
        'b' to Tengwar.UMBAR,
        'g' to Tengwar.UNGWE,
        'm' to Tengwar.MALTA,
        'n' to Tengwar.NUUMEN,
        'r' to Tengwar.ROOMEN,
        'l' to Tengwar.LAMBE,
        's' to Tengwar.SILME,
        'h' to Tengwar.HYARMEN,
        'f' to Tengwar.FORMEN,
        'v' to Tengwar.AMPA,
        'w' to Tengwar.VILYA,
        'y' to Tengwar.ANNA,
        'j' to Tengwar.ANGA
    )

    fun transliterate(text: String): String {
        val result = StringBuilder(text.length * 2)
        val word = StringBuilder()

        fun flush() {
            if (word.isNotEmpty()) {
                result.append(transliterateWord(word.toString()))
                word.setLength(0)
            }
        }

        for (raw in text) {
            val c = normalize(raw)
            if (c != null) {
                word.append(c)
            } else {
                flush()
                result.append(raw)
            }
        }
        flush()
        return result.toString()
    }

    /** Maps accented Latin letters to their plain ASCII equivalents. */
    private fun normalize(c: Char): Char? {
        if (c in 'a'..'z') return c
        return when (c) {
            in 'A'..'Z' -> c.lowercaseChar()
            'á', 'â', 'ä', 'à', 'ā', 'ã' -> 'a'
            'é', 'ê', 'ë', 'è', 'ē' -> 'e'
            'í', 'î', 'ï', 'ì', 'ī' -> 'i'
            'ó', 'ô', 'ö', 'ò', 'ō', 'õ' -> 'o'
            'ú', 'û', 'ü', 'ù', 'ū' -> 'u'
            'ý', 'ŷ', 'ÿ' -> 'y'
            'ñ' -> 'n'
            else -> null
        }
    }

    private fun transliterateWord(word: String): String {
        val units = ArrayList<Unit>()
        var i = 0

        while (i < word.length) {
            val c = word[i]

            // 1. Diphthong (two vowels, second element is the carrier)
            if (i + 1 < word.length && c in VOWELS && word[i + 1] in VOWELS) {
                val pair = "" + c + word[i + 1]
                if (pair in DIPHTHONGS) {
                    val carrier = when (word[i + 1]) {
                        'i', 'e' -> Tengwar.YANTA
                        else -> Tengwar.UURE
                    }
                    units.add(Unit(carrier).apply { vowelMark = Tengwar.tehtaFor(c) })
                    i += 2
                    continue
                }
            }

            // 2. Consonant digraph
            if (i + 1 < word.length) {
                val pair = "" + c + word[i + 1]
                val tengwa = DIGRAPHS[pair]
                if (tengwa != null) {
                    addConsonant(units, tengwa, c == word[i + 1])
                    i += 2
                    continue
                }
            }

            // 3. Vowel
            val tehta = Tengwar.tehtaFor(c)
            if (tehta != null) {
                addVowel(units, tehta)
                i++
                continue
            }

            // 4. Single consonant
            val tengwa = SINGLE_CONSONANTS[c]
            if (tengwa != null) {
                addConsonant(units, tengwa, false)
                i++
                continue
            }

            // Unknown character: keep as-is (still a separator-less letter).
            units.add(Unit(c).apply { letter = false })
            i++
        }

        val out = StringBuilder()
        for (u in units) out.append(u.render())
        return out.toString()
    }

    private fun addConsonant(units: MutableList<Unit>, tengwa: Char, doubled: Boolean) {
        val prev = units.lastOrNull()
        if (doubled || (prev != null && prev.letter && prev.tengwa == tengwa)) {
            // Long / doubled consonant -> doubler under the previous tengwa.
            if (prev != null && prev.letter) {
                prev.marks.append(Tengwar.DOUBLER)
                return
            }
        }
        units.add(Unit(tengwa))
    }

    private fun addVowel(units: MutableList<Unit>, tehta: Char) {
        val prev = units.lastOrNull()
        if (prev != null && prev.letter && prev.vowelMark == null) {
            prev.vowelMark = tehta
        } else {
            units.add(Unit(Tengwar.SHORT_CARRIER).apply { vowelMark = tehta })
        }
    }

    private class Unit(val tengwa: Char) {
        /** True when the unit is a tengwa (carrier or consonant), false for literal chars. */
        var letter: Boolean = true
        var vowelMark: Char? = null
        val marks = StringBuilder()

        fun render(): String = buildString {
            append(tengwa)
            vowelMark?.let { append(it) }
            append(marks)
        }
    }
}
