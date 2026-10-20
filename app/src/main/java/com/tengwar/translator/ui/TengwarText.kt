package com.tengwar.translator.ui

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import com.tengwar.translator.R
import com.tengwar.translator.tengwar.Tengwar

/**
 * Renders a mixed string produced by [com.tengwar.translator.tengwar.TengwarTransliterator]:
 * characters of the Tengwar private use area are drawn with the bundled Alcarin Tengwar
 * font, while separators (spaces, punctuation) use the regular text font.
 */
@Composable
fun TengwarText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: androidx.compose.ui.unit.TextUnit = LocalTextStyle.current.fontSize
) {
    val tengwarFont = remember { FontFamily(Font(R.font.alcarin_tengwar)) }
    val base = LocalTextStyle.current

    val annotated = remember(text, tengwarFont) {
        buildAnnotatedString {
            var i = 0
            while (i < text.length) {
                val isTengwa = Tengwar.isTengwa(text[i])
                var j = i
                while (j < text.length && Tengwar.isTengwa(text[j]) == isTengwa) j++
                val chunk = text.substring(i, j)
                if (isTengwa) {
                    withStyle(SpanStyle(fontFamily = tengwarFont)) { append(chunk) }
                } else {
                    append(chunk)
                }
                i = j
            }
        }
    }

    Text(
        text = annotated,
        modifier = modifier,
        style = base.copy(
            fontSize = fontSize,
            // Tengwar need extra vertical room: tehtar are written above the tengwa.
            lineHeight = fontSize * 2.1f
        )
    )
}
