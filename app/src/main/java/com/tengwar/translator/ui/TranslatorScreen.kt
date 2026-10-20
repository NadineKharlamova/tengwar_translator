package com.tengwar.translator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tengwar.translator.domain.Direction
import com.tengwar.translator.domain.TranslationResult
import com.tengwar.translator.tengwar.TengwarTransliterator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslatorScreen(viewModel: TranslatorViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Тенгвар-переводчик") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DirectionPicker(
                direction = viewModel.direction,
                onDirectionChange = viewModel::onDirectionChange
            )

            OutlinedTextField(
                value = viewModel.input,
                onValueChange = viewModel::onInputChange,
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                label = {
                    Text(
                        if (viewModel.direction == Direction.RU_TO_SINDARIN) "Текст на русском"
                        else "Текст на синдарине (латиницей)"
                    )
                },
                trailingIcon = {
                    if (viewModel.input.isNotEmpty()) {
                        IconButton(onClick = viewModel::clear) {
                            Icon(Icons.Filled.Close, contentDescription = "Очистить")
                        }
                    }
                }
            )

            Button(
                onClick = viewModel::translate,
                modifier = Modifier.fillMaxWidth(),
                enabled = viewModel.input.isNotBlank()
            ) {
                Text("Перевести")
            }

            viewModel.result?.let { ResultCard(it) }

            ExampleCard()

            Text(
                text = "Перевод выполняется в два шага: русский → английский → синдарин " +
                    "(и обратно). Синдарин показан латинской транслитерацией и тенгваром.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DirectionPicker(direction: Direction, onDirectionChange: (Direction) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = direction == Direction.RU_TO_SINDARIN,
            onClick = { onDirectionChange(Direction.RU_TO_SINDARIN) },
            label = { Text("Русский → Синдарин") }
        )
        FilterChip(
            selected = direction == Direction.SINDARIN_TO_RUSSIAN,
            onClick = { onDirectionChange(Direction.SINDARIN_TO_RUSSIAN) },
            label = { Text("Синдарин → Русский") }
        )
    }
}

@Composable
private fun ResultCard(result: TranslationResult) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (result.direction == Direction.RU_TO_SINDARIN) {
                SectionTitle("1. Английский (промежуточный)")
                Text(
                    text = result.english,
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider()

                SectionTitle("2. Синдарин — транслит")
                Text(
                    text = result.target,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider()

                SectionTitle("3. Синдарин — тенгвар")
                TengwarBlock(result.tengwar)
            } else {
                SectionTitle("1. Английский (промежуточный)")
                Text(
                    text = result.english,
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider()

                SectionTitle("2. Русский")
                Text(
                    text = result.target,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider()

                SectionTitle("Тенгвар (ввод)")
                TengwarBlock(result.tengwar)
            }

            if (result.unknown.isNotEmpty()) {
                Text(
                    text = "Нет в словаре: " + result.unknown.joinToString(", "),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
private fun TengwarBlock(tengwar: String) {
    TengwarText(
        text = if (tengwar.isBlank()) "—" else tengwar,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        fontSize = 34.sp
    )
}

@Composable
private fun ExampleCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Пример",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text("друг  →  friend  →  mellon", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "mellon в тенгваре:",
                style = MaterialTheme.typography.bodySmall
            )
            TengwarBlock(TengwarTransliterator.transliterate("mellon"))
            Text(
                text = "«mellon» = «друг»",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}
