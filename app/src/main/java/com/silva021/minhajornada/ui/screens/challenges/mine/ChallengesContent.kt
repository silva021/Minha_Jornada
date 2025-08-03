package com.silva021.minhajornada.ui.screens.challenges.mine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.domain.model.Challenges
import com.silva021.minhajornada.domain.extension.calculateChallengeDaysLeft
import com.silva021.minhajornada.domain.extension.calculateChallengeEndDate
import com.silva021.minhajornada.domain.extension.calculateChallengeProgress
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.DatabaseFake.challengesDto
import com.silva021.minhajornada.ui.theme.Palette

@Composable
fun ChallengesContent(
    challenges: Challenges,
    onCreateChallenge: () -> Unit,
    onUpdateChallengeProgress: () -> Unit,
    onSummaryChallengeClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
            .padding(16.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Desafios Ativos",
                    color = Palette.textPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Button(
                    colors = ButtonDefaults.buttonColors()
                        .copy(containerColor = Palette.backgroundColor),
                    onClick = onCreateChallenge
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar Desafio",
                        tint = Color.White
                    )
                }
            }
        }

        items(challenges.actives) {
            ChallengeItem(
                icon = Icons.Default.LocalFireDepartment,
                title = it.title,
                progress = it.calculateChallengeProgress(),
                daysLeft = it.calculateChallengeDaysLeft(),
                onClick = {
                    onUpdateChallengeProgress()
                }
            )
        }

        item {
            Text(
                text = "Continue assim, você está indo bem!",
                color = Palette.textSecondary,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(
                text = "Desafios Concluídos",
                color = Palette.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        items(challenges.completed) {
            CompletedChallengeItem(
                title = it.title,
                completionDate = "Concluído em ${it.calculateChallengeEndDate()}",
                onClick = {
                    onSummaryChallengeClick()
                }
            )
        }
    }
}


@Composable
fun ChallengeItem(
    icon: ImageVector,
    title: String,
    progress: Int,
    daysLeft: Int,
    onClick: () -> Unit = { /* Default action */ },
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Palette.cardBackground)
            .padding(16.dp)
            .clickable {
                onClick.invoke()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Palette.primaryColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Ícone do Desafio",
                    tint = Palette.primaryColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = Palette.textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "$daysLeft dias restantes",
                    color = Palette.textSecondary,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(horizontalAlignment = Alignment.End) {
                ProgressBar(progress = progress)
                Text(
                    text = "$progress%",
                    color = Palette.textSecondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun CompletedChallengeItem(
    title: String,
    completionDate: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .clip(RoundedCornerShape(8.dp))
            .background(Palette.cardBackground)
            .padding(16.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = Palette.textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = completionDate,
                    color = Palette.textSecondary,
                    fontSize = 14.sp
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun ProgressBar(progress: Int) {
    Box(
        modifier = Modifier
            .width(96.dp)
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Palette.accentColor)
    ) {
        Box(
            modifier = Modifier
                .width((96 * progress / 100).dp)
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Palette.primaryColor)
        )
    }
}


@Composable
@Preview
fun ChallengesContentPreview() {
    ChallengesContent(
        challenges = Challenges(
            actives = listOf(
                challengesDto.last().toDomain(),
            ),
            completed = listOf(
                challengesDto.first().toDomain()
            )
        ),
        onCreateChallenge = {},
        onUpdateChallengeProgress = {},
        onSummaryChallengeClick = {}
    )
}