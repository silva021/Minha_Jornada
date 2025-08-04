package com.silva021.minhajornada.ui.screens.help

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.domain.model.Faq
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header("Ajuda") { onBackPressed.invoke() }

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    text = "Primeiros Passos",
                    color = textPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            val firstSteps = listOf(
                Faq(
                    question = "O que é um desafio?",
                    answer = "Um desafio é um compromisso pessoal com um objetivo que você quer conquistar. Pode ser algo simples, como beber mais água todos os dias, ou algo maior, como correr uma maratona. O app ajuda você a acompanhar esse progresso de forma visual e motivadora."
                ),
                Faq(
                    question = "Como criar um desafio?",
                    answer = "Toque no botão '+' na barra superior do app. Preencha o título, uma breve descrição, defina a duração e, se quiser, adicione marcos importantes. Pronto! Agora é só começar."
                ),
                Faq(
                    question = "Como participar de um desafio?",
                    answer = "Acesse a aba 'Comunidades' para descobrir desafios públicos criados por outros usuários. Gostou de algum? Toque em 'Participar' e ele será adicionado à sua lista de desafios ativos."
                )
            )

            items(firstSteps) { faq ->
                ExpandableFAQItem(faq)
            }
        }
    }
}

@Composable
fun ExpandableFAQItem(faq: Faq) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardBackground
        ),
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = faq.question,
                    color = textPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = if (expanded) "Recolher" else "Expandir",
                    tint = textSecondary,
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(if (expanded) 180f else 0f)
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = faq.answer,
                    color = textSecondary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HelpScreenPreview() {
    HelpScreen({})
}