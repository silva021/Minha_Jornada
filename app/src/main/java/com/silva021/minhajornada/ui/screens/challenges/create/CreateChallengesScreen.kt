package com.silva021.minhajornada.ui.screens.challenges.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.DurationType
import com.silva021.minhajornada.ui.components.CategoriesFilter
import com.silva021.minhajornada.ui.components.CustomTextField
import com.silva021.minhajornada.ui.components.DurationFilter
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateChallengesScreen(
    onBackPressed: () -> Unit,
) {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val duration = remember { mutableStateOf("") }
    val notificationsEnabled = remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(CategoryType.FITNESS) }
    var selectedDuration by remember { mutableStateOf(DurationType.THREE_DAYS) }

    Column(
        modifier = Modifier
            .background(Palette.backgroundColor)
    ) {
        Header(
            title = "Criar Desafio",
            onBackPressed = onBackPressed
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.backgroundColor)
                .padding(horizontal = 16.dp)
        ) {

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Nome do Desafio",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )

                CustomTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    placeholder = "Ex: Desafio Fitness de 30 Dias",
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Descrição",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )

                CustomTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    placeholder = "Descreva o desafio...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Duração",
                color = textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            DurationFilter(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                selectedDuration = selectedDuration
            ) {
                selectedDuration = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Categorias",
                color = textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            CategoriesFilter(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                selectedCategory = selectedCategory
            ) {
                selectedCategory = it
            }

            Text(
                text = "Configurações",
                color = textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Palette.cardBackground)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Notificações",
                            color = textPrimary,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = if (notificationsEnabled.value) "Ativadas" else "Desativadas",
                            color = textSecondary,
                            fontSize = 14.sp
                        )
                    }
                    Switch(
                        checked = notificationsEnabled.value,
                        onCheckedChange = { notificationsEnabled.value = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Palette.primaryColor,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = accentColor
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                onClick = { /* Salvar alterações */ },
                modifier = Modifier.fillMaxWidth(),
                text = "Salvar Alterações",
                enabled = title.value.isNotBlank() && description.value.isNotBlank() && duration.value.isNotBlank(),
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun CreateChallengesScreenPreview() {
    CreateChallengesScreen(
        onBackPressed = {}
    )
}
