package com.tengwar.translator.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tengwar.translator.domain.Direction
import com.tengwar.translator.domain.TranslationResult
import com.tengwar.translator.domain.Translator

class TranslatorViewModel : ViewModel() {

    private val translator = Translator()

    var input by mutableStateOf("")
        private set

    var direction by mutableStateOf(Direction.RU_TO_SINDARIN)
        private set

    var result by mutableStateOf<TranslationResult?>(null)
        private set

    fun onInputChange(value: String) {
        input = value
    }

    fun onDirectionChange(value: Direction) {
        if (value == direction) return
        direction = value
        result = null
    }

    fun translate() {
        val text = input.trim()
        result = if (text.isEmpty()) null else translator.translate(text, direction)
    }

    fun clear() {
        input = ""
        result = null
    }
}
