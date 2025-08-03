package com.silva021.minhajornada.ui.screens.explorer.challengedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.domain.model.PublicChallenge
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun ExplorerChallengeDetailsScreen(
    challenge: PublicChallenge,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier.background(backgroundColor)
    ) {
        Header(
            "Detalhes do Desafio",
            onBackPressed
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            ChallengeImage(challenge)
            ChallengeDescription(challenge)
            RulesAndBenefitsSection(challenge)
            DurationSection(challenge)
        }
        JoinButton()
    }
}


@Composable
private fun ChallengeImage(challenge: PublicChallenge) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .padding(16.dp)
    ) {
        AsyncImage(
            model = challenge.imageUrl,
            contentDescription = "Imagem do desafio",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(
                text = challenge.title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
private fun ChallengeDescription(challenge: PublicChallenge) {
    Text(
        text = challenge.description,
        color = textSecondary,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        lineHeight = 24.sp
    )
}

@Composable
private fun RulesAndBenefitsSection(
    challenge: PublicChallenge
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(cardBackground)
            .padding(16.dp)
    ) {
        if (challenge.rules.isNotEmpty()) {
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = "Regras",
                    color = primaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                challenge.rules.forEach { rule ->
                    BulletPoint(text = rule)
                }
            }
        }

        if (challenge.benefits.isNotEmpty()) {
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = "Benefícios",
                    color = primaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                challenge.benefits.forEach { benefit ->
                    BulletPoint(text = benefit)
                }
            }
        }
    }
}

@Composable
private fun BulletPoint(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "•",
            color = textSecondary,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            color = textSecondary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun DurationSection(challenge: PublicChallenge) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Duração",
            color = primaryColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = "Duração",
                tint = primaryColor,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = "${challenge.duration.days} dias",
                color = textPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun JoinButton() {
    PrimaryButton(
        onClick = { /* Participar do desafio */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = "Participar do Desafio",
    )
    Spacer(Modifier.height(16.dp))
}

@Preview
@Composable
fun ExplorerChallengeDetailsPreview() {
    ExplorerChallengeDetailsScreen(
        DatabaseFake.publicChallenges.first().toDomain(),
        onBackPressed = {}
    )
}