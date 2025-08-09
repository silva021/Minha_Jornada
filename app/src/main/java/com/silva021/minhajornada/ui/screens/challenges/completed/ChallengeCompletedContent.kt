package com.silva021.minhajornada.ui.screens.challenges.completed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.ChallengeResult
import com.silva021.minhajornada.domain.model.ChallengeResult.FAILED
import com.silva021.minhajornada.domain.model.ChallengeResult.SUCCESS
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.SecondButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground

@Composable
fun ChallengeCompletedContent(
    challenge: Challenge,
    result: ChallengeResult,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            title = "Desafio Concluído",
            onBackPressed = onBackPressed
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CongratulationsSection(challenge, result)

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = when (result) {
                        SUCCESS -> "https://lh3.googleusercontent.com/aida-public/AB6AXuB6IV2MwBKRqDJXBSP-V2ponakBZn6RkBJvgvofNIlXqAM5AKyBzT0c8VzAP3rDHgtzWmzaH6COZLyinsazbDNDSFAJK-HwqUCNQAwzowtEOBTDO0Lj21K38lPdD2SjSEFiJxeUekvUQ1-VoVMm1HKCKiHsyEgVYNoKj7mHjkpRIAFYs39t3oMyYWomEL3-tnkc6X5YjZp3BkdLW2XuzxEqiEEAyaeUZtTFeZQG06s4CD_Of1OiNcVZxdVXwpJJVgrry0uwn-pas18"
                        FAILED -> "https://lh3.googleusercontent.com/aida-public/AB6AXuDNaILYqcRrJSIUE0ClGPEMO_LwSv8U1hFPzm3BddbR032-N6RcXiYvLZO-g1eULpNhIqaprhiyY_iKPwVYE5qtg9PYFxcRhpuO8JWyAjBhMr7oN0K-o42E7gcRA-batudjcJybJWt_18M1UWLr49OOeAV7LpgPqrwsxARBANrPbBfKVmAWR9S8P8jwDWtUqopPN7Oa3Tv8Fbx5bZaAWStj3ExT09H0IoAkynwZpgJDfHV1-D-ihdEyPlMcn-Y-xzPnmfhxqYmXk2M"
                    },
                    contentDescription = "Imagem do desafio concluído",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            InsightsCard(result)

            Spacer(modifier = Modifier.height(16.dp))

            ActionButtons(onClick = onBackPressed)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun InsightsCard(
    challengeResult: ChallengeResult,
) {
    Surface(
        color = cardBackground,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Reflexões",
                color = Palette.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = when (challengeResult) {
                    SUCCESS -> "O fim de um desafio pode ser o começo de um novo hábito. Qual será seu próximo passo?"
                    FAILED -> "Nem tudo sai como planejado — e tudo bem. O que esse desafio te ensinou? Existe algo que você faria diferente em uma nova tentativa?"
                },
                color = Palette.textSecondary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ActionButtons(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PrimaryButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            text = "Voltar para o Início"
        )
    }
}

@Composable
fun CongratulationsSection(
    challenge: Challenge,
    challengeResult: ChallengeResult,
) {
    Box(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        if (challengeResult == ChallengeResult.SUCCESS) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xFF53D22C).copy(alpha = 0.3f),
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.TopStart)
                    .offset(x = (-16).dp, y = (-16).dp)
            )

            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xFF53D22C).copy(alpha = 0.3f),
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 16.dp, y = 16.dp)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = when (challengeResult) {
                    SUCCESS -> "Parabéns, você conseguiu!"
                    FAILED -> "Não desista!"
                },
                color = Palette.textPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = when (challengeResult) {
                    SUCCESS -> "Você completou com sucesso o '${challenge.title}'. Seu esforço e dedicação valeram a pena. Continue com o bom trabalho!"
                    FAILED -> "O fim de um desafio pode ser o começo de um novo hábito. Qual será seu próximo passo?"
                },
                color = Palette.textSecondary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    MaterialTheme {
        ChallengeCompletedContent(
            challenge = DatabaseFake.challenges.first(),
            onBackPressed = {},
            result = ChallengeResult.SUCCESS
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FailedScreenPreview() {
    MaterialTheme {
        ChallengeCompletedContent(
            challenge = DatabaseFake.challenges.first(),
            onBackPressed = {},
            result = ChallengeResult.FAILED,
        )
    }
}