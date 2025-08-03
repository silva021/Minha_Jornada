package com.silva021.minhajornada.ui.screens.communities.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun NewPostArea(
    profile: Profile
) {
    var postText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Palette.cardBackground)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            profile.profilePictureUrl.let {
                AsyncImage(
        //                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAXjk17jzzzp3okto8YCUGaAjfMMcCN6KMAM9AOhegn-UnaeKEoSki8P0dQJH8Dlh7DYeeAci9T9bP6_SlPtQuVjSSwdi6ZkreCUiVHd6T2JeefSWC_xAemoj2y5b3ju4GaDpCa64qlF87sGjWTMt22OhP49SaNqOdDlhUF-RUnSpA076Sa8K5PQ8tsUZdnhkayWXDFLb7vBWNJRe43jAkNYZdD0GH4L5iHgUK6Rl9WR7NJo1ni5BkPoYXShM4MyJ_zq0_Q-c1Vs_4",
                    model = it,
                    contentDescription = "Seu perfil",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))
            }

            BasicTextField(
                value = postText,
                onValueChange = { postText = it },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(backgroundColor, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 14.dp),
                decorationBox = { innerTextField ->
                    if (postText.text.isEmpty()) {
                        Text(
                            "Compartilhe seus pensamentos...",
                            color = textSecondary,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* Adicionar imagem */ }) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Adicionar imagem",
                    tint = textSecondary
                )
            }

            PrimaryButton(
                onClick = { /* Publicar */ },
                shape = RoundedCornerShape(8.dp),
                text = "Publicar"
            )
        }
    }
}