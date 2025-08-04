package com.silva021.minhajornada.ui.screens.challenges.update

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.ChallengeResult
import com.silva021.minhajornada.domain.model.CheckInStatus
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.components.CustomTextField
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.SecondButton
import com.silva021.minhajornada.ui.components.StatusFilter
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateChallengeProgressScreen(
    challenge: Challenge,
    onCompletedDay: (ChallengeResult) -> Unit,
    onCompleteChallenge: (ChallengeResult) -> Unit,
    onBackPressed: () -> Unit,
) {
    val observationText = remember { mutableStateOf("") }
    var statusSelected by remember { mutableStateOf(CheckInStatus.SUCCESS) }

    Column(
        modifier = Modifier
            .background(Palette.backgroundColor)
    ) {
        Header(
            title = "Atualizar Progresso",
            onBackPressed = onBackPressed
        )
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
                Text(
                    text = "Desafio ${challenge.title}",
                    color = textPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = challenge.categoryType.label,
                    color = textSecondary,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Criado(a) por " + challenge.owner.name,
                    color = textSecondary,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                    text = challenge.description,
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

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = observationText.value,
                onValueChange = { observationText.value = it },
                placeholder = "Como foi? Alguma dificuldade ou observação?",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Status",
                color = textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            StatusFilter(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                checkInStatusSelected = statusSelected,
                onCheckInStatusSelected = { statusSelected = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Concluir o dia",
                onClick = {
                    onCompletedDay.invoke(ChallengeResult.SUCCESS)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            SecondButton(
                text = "Concluir desafio",
                onClick = {
                    onCompleteChallenge.invoke(ChallengeResult.FAILED)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}


@Preview
@Composable
fun UpdateChallengeProgressScreenPreview() {
    UpdateChallengeProgressScreen(
        challenge = DatabaseFake.challengesDto[0].toDomain(),
        onBackPressed = {},
        onCompletedDay = {},
        onCompleteChallenge = {}
    )
}