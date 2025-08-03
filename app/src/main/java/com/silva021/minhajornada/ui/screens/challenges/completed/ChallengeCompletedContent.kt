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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.SecondButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground

@Composable
fun ChallengeCompletedContent(
    title: String,
    description: String,
    insightDescription: String,
    imageUrl: String,

    isSuccess: Boolean,

    primaryButtonText: String,
    primaryButtonOnClick: () -> Unit,
    secondaryButtonText: String?,
    secondaryButtonOnClick: (() -> Unit)?,

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
            CongratulationsSection(
                title,
                description,
                isSuccess
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Imagem do desafio concluído",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            InsightsCard(
                insightDescription
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButtons(
                primaryButtonText = primaryButtonText,
                primaryButtonOnClick = primaryButtonOnClick,
                secondaryButtonText = secondaryButtonText,
                secondaryButtonOnClick = secondaryButtonOnClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun InsightsCard(
    insightDescription: String
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
                text = insightDescription,
                color = Palette.textSecondary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ActionButtons(
    primaryButtonText: String,
    primaryButtonOnClick: () -> Unit,
    secondaryButtonText: String?,
    secondaryButtonOnClick: (() -> Unit)?,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PrimaryButton(
            onClick = primaryButtonOnClick,
            modifier = Modifier.fillMaxWidth(),
            text = primaryButtonText
        )

        secondaryButtonText?.let {
            SecondButton(
                onClick = { secondaryButtonOnClick?.invoke() },
                modifier = Modifier.fillMaxWidth(),
                text = it
            )
        }
    }
}

@Composable
fun CongratulationsSection(
    title: String,
    description: String,
    isSuccess: Boolean = false,
) {
    Box(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        if (isSuccess) {
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
                text = title,
                color = Palette.textPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                color = Palette.textSecondary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
