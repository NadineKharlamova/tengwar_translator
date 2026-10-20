package com.tengwar.translator.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class LexiconTest {

    @Test
    fun inversionsAreDerived() {
        assertEquals("mellon", Lexicon.enSin["friend"])
        assertEquals("friend", Lexicon.sinEn["mellon"])
        assertEquals("друг", Lexicon.enRu["friend"])
    }

    @Test
    fun maxPhraseWordsIsAtLeastOne() {
        assertEquals(3, Lexicon.maxPhraseWords)
    }
}
