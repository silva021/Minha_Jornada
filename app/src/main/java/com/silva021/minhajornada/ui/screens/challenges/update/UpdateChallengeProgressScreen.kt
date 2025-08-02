package com.silva021.minhajornada.ui.screens.challenges.update

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateChallengeProgressScreen(
    onBackPressed: () -> Unit,
) {
    val observationText = remember { mutableStateOf("") }

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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = rememberAsyncImagePainter(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCjAsJPAh34xYmsJb1nQ7zQ4kOorAExSEv4Ef0d-Ja1b6NdUhbXqDPbiE44jE6oEUlfjPlvVNn8FDiQ964I47DrNQUP2h1rEXxZWCRsaOwlXkNs667Peey5nmsbZrp2-JvGyjY8kMpYaoC2jBzNiMwZ0Suy5Qay6D99JcazlcLjwQMqhOjlEJ65lrieRcvU93Q-_7a6J6In6C9lqod_CUdt35RQv8DjQ48lkdDI178yVCUGnE9hlJSQW2lq4kkqksRH38ZbnOp6Q64"
                    ),
                    contentDescription = "Imagem do Desafio",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(RoundedCornerShape(24.dp))
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Desafio Fitness de 30 Dias",
                    color = textPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Fitness",
                    color = textSecondary,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Criado por Alex",
                    color = textSecondary,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Tarefa do dia
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(accentColor)
                    .padding(20.dp)
            ) {
                Text(
                    text = "Dia 15",
                    color = textPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tarefa de hoje: Complete um treino HIIT de 30 minutos. Foque em intervalos de alta intensidade com curtos períodos de descanso. Lembre-se de se hidratar e ouvir seu corpo.",
                    color = textSecondary,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Observações",
                color = textSecondary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))


            TextField(
                value = observationText.value,
                onValueChange = { observationText.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = {
                    Text(
                        text = "Como foi? Alguma dificuldade ou observação?",
                        color = textSecondary
                    )
                },
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = accentColor,
                    unfocusedContainerColor = accentColor,
                    disabledContainerColor = accentColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Palette.primaryColor,
                    focusedTextColor = textPrimary,
                    unfocusedTextColor = textPrimary,
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            PrimaryButton(
                text = "Marcar como concluído",
                onClick = { /* Ação ao marcar como concluído */ },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}


@Preview
@Composable
fun UpdateChallengeProgressScreenPreview() {
    UpdateChallengeProgressScreen(
        onBackPressed = {}
    )
}