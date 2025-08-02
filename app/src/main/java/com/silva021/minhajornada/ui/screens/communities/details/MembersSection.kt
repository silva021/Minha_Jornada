package com.silva021.minhajornada.ui.screens.communities.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary

@Composable
fun MembersSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Membros (1.2k)",
            color = textPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Fotos dos membros
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy((-12).dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Membros 1-5
            listOf(
                "https://lh3.googleusercontent.com/aida-public/AB6AXuDEJohM6Dj_bPTvHhQgvP357ZzYO5bk7ugnZPtoOZw9XiGZn4EBOWXZjVzNdWtKamiqBjRkBQ4WuirY6ZZWEt4CtEGbAGvUL4ucKlU6P-g2j1b5SHeYcKGkUUx4CALoe1tNbP6CO1uzajW3KMDqtRRyVd7RtNVJ2bfNvExIeKMHjS75AaOf-ArOIvJ9_1CwUE3Uu8xmyHUUeVUXG-TePaVysVWjOkgAv8FgRvNSnTNjdedLLYszLjEzrjYAqHAgFxT1Z1oXCUcOx08",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuAt0EzC8AxnCwke4cIA2ZipehjRMIt6Cj_FxcVqkOKm-I1P5u_37HDeIiYuemjyPObA-t_5Ce0dPaLh6m3Tk3AJ5mHJACSuI_eWANmcfhepLa-4LBuGYI8Lhm7KJHtXGfvtvTRsxFXfk8r8bwxT8-EENnFQ-SAHWOfwOT7C21K6ZnMKujxed4Gt2A3pubMIjSbLzBDutyhEwpTJ7VqNkTCPOvReLZ1yjI8BRfMaDhR281qWXBHLVbwTX2nn7jqTuKCvgQwUW8MUqmY",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuAGue32nmZFBKh1TKfY9h_6_w2DmiCfyElw0DY-1_Kv7DfXm9gVLZtAS9zYOVx2KNIhGVbgnbVUuj_NTq1yBFaT-6xJ0JiKxRQVIramXKZPrIVKo8zA1uI5jb28rEgFSnfof7Fuv_hXHFrpUMMaK1SMQJHQQt8r8QLldfhLjtgM6IjZSmb3jqafh2Ly1kw329ZTbuhgx9s2K3ChZrwilN59x55UNLdsMFdcscRofr35BkYT0CV8iADYFso__dJHKloYL5bxcYz3o-s",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuBYujPPO0uTpzONf3vlcqWUxNAnflOwO-FM3zxtG8wy3hKM6tDyqlKaVJ8NEw-YTqUkaqyBVwWgMkDeOc7pTbRnIo6Hr9IxEhExiI3PBvc8fG4wa7UJe8bkv7aXamTdZns9a0bByxFL_1ffEjXqcHilDRW0XNBS5X0fqs2vhjF6LtfJ8wxBOXIwAuSR574vekTmHZ4sLoKcRKCXEXUGGBQhAmN4UvD1IUAq3iRHIUwCIoKja8CtAkZ0ssbBwwnfHGSRCbeSnYJ22L8",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuD1a_lpz9S-QyI2epB-n4sr_uFompN6-zZB5oaJCKqqrwqi8jWJtxsyQBJtFiBD8SONtsxH8A8elHwxCOEANIxJl2Xx9e3u3wadxhoN0k_h6_KYj9QlvA4bmhkLP9iitDpPzAgYquX0EzhBQSKnnscCU7usnrXo7djzdD7Z2_I2a3LizS0OQMimfqvYzESFbnmSGvb4KVDDRa_KtXWJIMEWz1h3VXAUJB4VCaa_qVUj0ZZvKKT7aDfq5PVCKJwVUdmmzzSQzbFBn54"
            ).forEach { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Membro da comunidade",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(2.dp, backgroundColor, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}