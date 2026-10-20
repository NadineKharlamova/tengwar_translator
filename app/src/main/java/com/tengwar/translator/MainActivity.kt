package com.tengwar.translator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tengwar.translator.ui.TranslatorScreen
import com.tengwar.translator.ui.theme.TengwarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TengwarTheme {
                TranslatorScreen()
            }
        }
    }
}
