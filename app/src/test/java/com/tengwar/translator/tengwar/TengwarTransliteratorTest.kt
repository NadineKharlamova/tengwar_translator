package com.tengwar.translator.tengwar

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TengwarTransliteratorTest {

    @Test
    fun wordBecomesTengwar() {
        val result = TengwarTransliterator.transliterate("mellon")
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { Tengwar.isTengwa(it) })
    }

    @Test
    fun spacesArePreserved() {
        val result = TengwarTransliterator.transliterate("mae govannen")
        assertTrue(result.contains(' '))
    }

    @Test
    fun doubledConsonantUsesDoubler() {
        val result = TengwarTransliterator.transliterate("mellon")
        assertTrue(result.contains(Tengwar.DOUBLER))
    }

    @Test
    fun vowelCarriesOnPrecedingConsonant() {
        assertEquals("" + Tengwar.NUUMEN + Tengwar.THREE_DOTS_ABOVE, TengwarTransliterator.transliterate("na"))
    }

    @Test
    fun leadingVowelUsesShortCarrier() {
        val result = TengwarTransliterator.transliterate("elen")
        assertTrue(result.startsWith("" + Tengwar.SHORT_CARRIER + Tengwar.ACUTE))
    }

    @Test
    fun caseIsNormalized() {
        assertEquals(
            TengwarTransliterator.transliterate("mellon"),
            TengwarTransliterator.transliterate("MELLON")
        )
    }
}
