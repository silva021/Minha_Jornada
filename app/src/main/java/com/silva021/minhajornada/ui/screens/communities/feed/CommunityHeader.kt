package com.silva021.minhajornada.ui.screens.communities.feed

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
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun CommunityHeader(
    community: Community
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
//                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuD9-tV5n5JOQL4OJtNs4OgJv08Y4JvKstcFqQimD4MZw7Uk4-XFDOk93zlmDd8sVHSYtAAsXlPw-kj_N9oJFIzr6cmoGRUQ63pPPaEINbTUxf_MxHDb-kRyj4uRRr-j_0_CfRQCyvJMdzdk9pye5XoJisAAku8DLhn-2jpgXnis8U1tZcjYIISzJAF1VIk5U2nuC193dgim_JWGWhKVnA_Pb--Uf1zrEYn8ZH6FJO2aJeQ5NlGd1qJt7lmocn79iwsVdcdTyetMDI8",
                model = community.imageUrl,
                contentDescription = "Foto da comunidade",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
//                    text = "Viciados em Fitness",
                    text = community.name,
                    color = textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${community.members} membros", color = textSecondary, fontSize = 14.sp
                )
            }
        }

        PrimaryButton(
            text = "Sair",
            shape = RoundedCornerShape(8.dp),
            onClick = { /* Sair da comunidade */ },
        )
    }
}
