package com.tengwar.translator.domain

import com.tengwar.translator.tengwar.Tengwar
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TranslatorTest {

    private val translator = Translator()

    @Test
    fun russianDrugBecomesMellon() {
        val result = translator.translate("друг", Direction.RU_TO_SINDARIN)
        assertEquals("friend", result.english)
        assertEquals("mellon", result.target)
        assertTrue(result.tengwar.all { Tengwar.isTengwa(it) })
        assertTrue(result.unknown.isEmpty())
    }

    @Test
    fun sindarinMellonBecomesRussianDrug() {
        val result = translator.translate("mellon", Direction.SINDARIN_TO_RUSSIAN)
        assertEquals("friend", result.english)
        assertEquals("друг", result.target)
    }

    @Test
    fun unknownWordsAreReported() {
        val result = translator.translate("друг zzz", Direction.RU_TO_SINDARIN)
        assertTrue(result.unknown.contains("zzz"))
    }

    @Test
    fun phraseIsTranslatedAsAWhole() {
        val result = translator.translate("мой друг", Direction.RU_TO_SINDARIN)
        assertEquals("my friend", result.english)
        assertEquals("mellon nin", result.target)
    }
}
