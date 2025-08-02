package com.silva021.minhajornada.ui.screens.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.inputBackground
import com.silva021.minhajornada.ui.theme.Palette.inputBorder
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    onBackPressed: () -> Unit,
) {
    var category by remember { mutableStateOf("") }
    var feedbackText by remember { mutableStateOf("") }
    var isCategoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf("Relatar Bug", "Solicitar Funcionalidade", "Sugestão", "Outro")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header { onBackPressed.invoke() }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Como podemos melhorar?",
                color = textPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = "Categoria",
                    color = textSecondary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = isCategoryExpanded,
                    onExpandedChange = { isCategoryExpanded = !isCategoryExpanded }) {
                    TextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCategoryExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                            .clip(RoundedCornerShape(12.dp))
                            .background(inputBackground)
                            .border(1.dp, inputBorder, RoundedCornerShape(12.dp)),
                        colors = TextFieldDefaults.colors().copy(
                            focusedContainerColor = inputBackground,
                            unfocusedContainerColor = inputBackground,
                            disabledContainerColor = inputBackground,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = textPrimary,
                            focusedTextColor = textPrimary,
                            unfocusedTextColor = textPrimary,
                        ),
                        placeholder = { Text("Selecione uma categoria") })

                    ExposedDropdownMenu(
                        expanded = isCategoryExpanded,
                        onDismissRequest = { isCategoryExpanded = false }) {
                        categories.forEach { item ->
                            DropdownMenuItem(text = { Text(item) }, onClick = {
                                category = item
                                isCategoryExpanded = false
                            })
                        }
                    }
                }
            }

            // Campo de feedback
            Column(modifier = Modifier.padding(bottom = 24.dp)) {
                Text(
                    text = "Descreva seu feedback",
                    color = textSecondary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TextField(
                    value = feedbackText,
                    onValueChange = { feedbackText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(inputBackground)
                        .border(1.dp, inputBorder, RoundedCornerShape(12.dp)),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = inputBackground,
                        unfocusedContainerColor = inputBackground,
                        disabledContainerColor = inputBackground,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = textPrimary,
                        focusedTextColor = textPrimary,
                        unfocusedTextColor = textPrimary,
                    ),
                    placeholder = { Text("Conte-nos o que está pensando...") })
            }

            PrimaryButton(
                text = "Enviar Feedback",
                onClick = { /* Ação ao enviar feedback */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun FeedbackScreenPreview() {
    FeedbackScreen(
        onBackPressed = {}
    )
}