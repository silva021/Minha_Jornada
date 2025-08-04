package com.silva021.minhajornada.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.profilesDTO
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun InputArea(
    profilePictureUrl: String? = null,
    postText: String,
    onPostTextChange: (String) -> Unit,
    placeholder: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        profilePictureUrl?.let {
            AsyncImage(
                model = profilePictureUrl,
                contentDescription = "Seu perfil",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        BasicTextField(
            value = postText,
            onValueChange = { onPostTextChange.invoke(it) },
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .background(backgroundColor, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            decorationBox = { innerTextField ->
                if (postText.isEmpty()) {
                    Text(
                        placeholder,
                        color = textSecondary,
                        fontSize = 14.sp
                    )
                }
                innerTextField()
            },
            textStyle = TextStyle.Default.copy(color = White)
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = { /* Adicionar imagem */ }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Adicionar imagem",
                tint = if (postText.isEmpty()) textSecondary else primaryColor,
            )
        }
    }
}

@Composable
@Preview
fun NewPostAreaPreview() {
    Column {
        val profile = profilesDTO.first().toDomain()
        InputArea(
            profilePictureUrl = profile.profilePictureUrl,
            postText = "",
            placeholder = "O que você está pensando?",
            onPostTextChange = {}
        )

        InputArea(
            profilePictureUrl = null,
            postText = "",
            placeholder = "O que você está pensando?",
            onPostTextChange = {}
        )
    }
}