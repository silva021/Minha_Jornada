package com.silva021.minhajornada.ui.screens.challenges.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.CheckIn
import com.silva021.minhajornada.domain.model.CheckInStatus
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.theme.Palette.accentColor
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.errorColor
import com.silva021.minhajornada.ui.theme.Palette.successColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ChallengeSummaryContent(
    challenge: Challenge,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header(
            title = "Resumo do Desafio",
            onBackPressed = onBackPressed
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            ChallengeHeader()

            Spacer(modifier = Modifier.height(8.dp))

            ProgressSummary(challenge)
            DailyReports(challenge.checkins)
            FinalThoughts()
        }
    }
}

@Composable
private fun ChallengeHeader() {
    Text(
        text = "Abrace a Jornada",
        color = textPrimary,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "\"A única jornada impossível é aquela que você nunca começa.\"",
        color = textSecondary,
        fontSize = 18.sp,
        fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun ProgressSummary(challenge: Challenge) {
    Text(
        text = "Você completou ${challenge.checkins.filter { it.status == CheckInStatus.SUCCESS }.size} de ${challenge.durationType.days} dias do seu desafio. Continue avançando!",
        color = textPrimary,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
private fun ColumnScope.DailyReports(
    checkIns: List<CheckIn>,
) {
    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = "Relatórios Diários",
            color = textPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

        LazyColumn {
            items(checkIns.reversed()) {
                ReportItem(it)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun ReportItem(
    checkIn: CheckIn,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(accentColor, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = if (checkIn.status == CheckInStatus.SUCCESS) "✔" else "✖",
            color = if (checkIn.status == CheckInStatus.SUCCESS) successColor else errorColor,
            fontSize = 24.sp,
            modifier = Modifier.padding(end = 16.dp)
        )

        Column {
            Text(
                text = "Dia ${checkIn.day}",
                color = textPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )


            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            Text(
                text = dateFormat.format(checkIn.date.toDate()),
                color = textSecondary,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.width(140.dp),
            text = checkIn.note,
            color = textSecondary,
            fontSize = 14.sp
        )
    }
}

@Composable
fun FinalThoughts() {
    Column(
        modifier = Modifier.padding(bottom = 32.dp)
    ) {
        Text(
            text = "Pensamentos Finais",
            color = textPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardBackground, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Este desafio foi uma experiência transformadora. Aprendi muito sobre mim mesmo e minha capacidade de crescimento. Estou animado para continuar esta jornada de autodescoberta.",
                color = textSecondary,
                fontSize = 16.sp,
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle
            )
        }
    }
}

@Composable
@Preview
fun ChallengeSummaryContentPreview() {
    ChallengeSummaryContent(
        challenge = DatabaseFake.challenges.first(),
        onBackPressed = {}
    )
}