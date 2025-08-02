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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary


@Composable
fun ExplorerChallengeDetailsScreen(
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier.background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Header(
                "Detalhes do Desafio",
                onBackPressed
            )
            ChallengeImage()
            ChallengeDescription()
            RulesAndBenefitsSection()
            DurationSection()
        }
        JoinButton()
    }
}


@Composable
private fun ChallengeImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .padding(16.dp)
    ) {
        AsyncImage(
            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAkc7caumNKguxFQi23XXLIF2nAooak9zqJPurvCUxusrtDiHPpgIn2G4GDo37dIR_ml1-FdqEvvUrA98xI9Tx2YSkx9uzWHllghd9h0-zICGTGjcFQJUMvDnArQRq5Vh_hSg8dyVPucLX5oh8kVn6eAvUO-bicp50aUqpkT_HhbEWPMqluI0-UBp1I-PZuMNsDfcISibPj6ZcP8j1sM90poD2kDfuSSEpzv7R9HdCpTyCL8aRgFy4S816kJxN_vQjPcAW8dNIHPpI",
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
                text = "Corra 5km todos os dias por um mês",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
private fun ChallengeDescription() {
    Text(
        text = "Este desafio foi projetado para ajudá-lo a construir um hábito consistente de corrida. Ao correr 5km todos os dias durante um mês, você melhorará sua saúde cardiovascular, aumentará sua resistência e alcançará um marco significativo de condicionamento físico.",
        color = textSecondary,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        lineHeight = 24.sp
    )
}

@Composable
private fun RulesAndBenefitsSection() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(cardBackground)
            .padding(16.dp)
    ) {
        // Regras
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Regras",
                color = primaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            BulletPoint(text = "Corra 5km (3.1 milhas) todos os dias")
            BulletPoint(text = "Registre suas corridas usando um aplicativo ou dispositivo de fitness")
            BulletPoint(text = "Compartilhe seu progresso com a comunidade")
        }

        // Benefícios
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Benefícios",
                color = primaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            BulletPoint(text = "Melhora da saúde cardiovascular")
            BulletPoint(text = "Aumento da resistência e stamina")
            BulletPoint(text = "Controle de peso")
            BulletPoint(text = "Clareza mental e redução do estresse")
            BulletPoint(text = "Senso de realização")
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
private fun DurationSection() {
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
                text = "30 dias",
                color = textPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun JoinButton() {
    Button(
        onClick = { /* Participar do desafio */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor,
            contentColor = Color.Black
        ),
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(48.dp)
    ) {
        Text(
            text = "Participar do Desafio",
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(Modifier.height(16.dp))
}

@Preview
@Composable
fun ExplorerChallengeDetailsPreview() {
    ExplorerChallengeDetailsScreen(
        onBackPressed = {}
    )
}