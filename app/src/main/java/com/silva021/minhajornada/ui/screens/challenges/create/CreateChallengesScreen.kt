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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.SecondButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateChallengesScreen(
    onBackPressed: () -> Unit,
    ) {

    val templateName = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val duration = remember { mutableStateOf("") }
    val isPublic = remember { mutableStateOf(false) }
    val notificationsEnabled = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Palette.backgroundColor)
    ) {
        Row {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = textPrimary
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.backgroundColor)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Nome do modelo
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Nome do Modelo",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )
                TextField(
                    value = templateName.value,
                    onValueChange = { templateName.value = it },
                    placeholder = { Text("Ex: Desafio Fitness de 30 Dias") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = accentColor,
                        unfocusedContainerColor = accentColor,
                        disabledContainerColor = accentColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = textPrimary,
                        focusedTextColor = textPrimary,
                        unfocusedTextColor = textPrimary,
//                        placeholderColor = textSecondary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Descrição
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Descrição",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )
                TextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    placeholder = { Text("Descreva o desafio...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = accentColor,
                        unfocusedContainerColor = accentColor,
                        disabledContainerColor = accentColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = textPrimary,
                        focusedTextColor = textPrimary,
                        unfocusedTextColor = textPrimary,
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Duração
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Duração",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )
                TextField(
                    value = duration.value,
                    onValueChange = { duration.value = it },
                    placeholder = { Text("Ex: 30 dias") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = accentColor,
                        unfocusedContainerColor = accentColor,
                        disabledContainerColor = accentColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = textPrimary,
                        focusedTextColor = textPrimary,
                        unfocusedTextColor = textPrimary,
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Configurações
            Text(
                text = "Configurações",
                color = textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Palette.cardBackground)
            ) {
                // Visibilidade
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Visibilidade",
                            color = textPrimary,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = if (isPublic.value) "Público" else "Privado",
                            color = textSecondary,
                            fontSize = 14.sp
                        )
                    }
                    Switch(
                        checked = isPublic.value,
                        onCheckedChange = { isPublic.value = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Palette.primaryColor,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = accentColor
                        )
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = accentColor)

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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Palette.backgroundColor)
                    .padding(16.dp)
            ) {
                PrimaryButton(
                    onClick = { /* Salvar alterações */ },
                    modifier = Modifier.fillMaxWidth(),
                    text = "Salvar Alterações"
                )

                Spacer(modifier = Modifier.height(8.dp))

//                if (false) {
//                    SecondButton(
//                        onClick = { /* Usar modelo */ },
//                        modifier = Modifier.fillMaxWidth(),
//                        text = "Usar Modelo"
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    Button(
//                        onClick = { /* Excluir modelo */ },
//                        modifier = Modifier.fillMaxWidth(),
//                        shape = RoundedCornerShape(50),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color.Transparent,
//                            contentColor = Palette.primaryColor
//                        )
//                    ) {
//                        Text("Excluir Modelo")
//                    }
//                }
            }
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