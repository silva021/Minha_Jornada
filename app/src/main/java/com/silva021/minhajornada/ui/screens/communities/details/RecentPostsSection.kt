package com.silva021.minhajornada.ui.screens.communities.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary


@Composable
private fun RecentPostsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Posts Recentes",
            color = textPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PostItem(
                authorImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuApO-KPoe4UGAD8q7ZRbdpbefrTfz0yPdEoPLSuin_sKHi2guabv47lPf2HrqQNfRfdvZlCi9iinxNr-pUPJh8vpITE2sFEvYye4g9Ubp_GJgjjPKMXYUkQt-nNEscsXL4X20fqmx2jkRuGpym-NbBEkIdG0ZCAVG4iPjjVQ7H32Zoexi3LTBQgnNaLBsB-_121knmG4pCtCAEpePdbfGAvDTySVafibB42wjOELGEZBCt72MXyPVHShJ4xgkwnnA97p81qdPAOEUY",
                authorName = "Ethan",
                timeAgo = "2 dias atrás",
                content = "Estou começando um desafio de fitness de 30 dias. Alguém quer participar?"
            )

            PostItem(
                authorImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCun1wAJaX3scWueGQna9gD5HAcCNmN1f_7LptFsbSRUVtCv5HQlW-KLvylFYRt5zL9IKCSdqK6rQr9F58i2CyUDxTftKQaPwDPGnNQPLmBVgUvtHWLxA_3xoJdFevg0bIpqvlxV5aFKf0NUkWhl11K-Y2nPFEdjFwc4Awfa3Yc_GKr0jRj4O9YbRyhZ-DKVaYuedO26OUYqmDb_UTINI2LyGA06qisd3fSKf__8XDGcUNefS0UAGV2zfU9WuWgbrtbD0xE0qyg0no",
                authorName = "Sophia",
                timeAgo = "3 dias atrás",
                content = "Estou procurando um desafio para melhorar minhas habilidades de fotografia. Alguma ideia?"
            )
        }
    }
}

@Composable
private fun PostItem(
    authorImage: String,
    authorName: String,
    timeAgo: String,
    content: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(cardBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = authorImage,
            contentDescription = "Foto do autor",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = authorName,
                    color = textPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = timeAgo,
                    color = textSecondary,
                    fontSize = 12.sp
                )
            }

            Text(
                text = content,
                color = textSecondary,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}