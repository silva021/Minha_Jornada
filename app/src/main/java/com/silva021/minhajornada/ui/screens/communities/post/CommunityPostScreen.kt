package com.silva021.minhajornada.ui.screens.communities.post

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.domain.model.Comment
import com.silva021.minhajornada.ui.DatabaseFake.comments
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.likeColor
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun CommunityPostScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header("Postagem") { }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                PostContent()
            }

            item {
                CommentsHeader()
            }

            items(comments) { comment ->
                CommentItem(comment)
                Spacer(Modifier.height(4.dp))
            }
        }
        CommentInput()
    }
}

@Composable
private fun PostContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardBackground)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAFCKSs_hFuBUeyu7V7eT5xBM9EgAUub6r18KvMj_WhZn3cUToGxu05C020U7uIK2IMNxhue07-itkXt_RIWndr2l4RIj57-ikDqdPHmUxvbBVSJxLw2lorzXKGWIgjPTDYPcEDw2ONkcz1tbEJ3ofbiay1WX0qoqJrhB-Plz_fn1Hs2xGZgTaTuiRAUzhP8ldP1Ne5qpR8I8ZCDYA9ny1vsskXsVbGlr_2ulcHvcdK6huz-JncK6zCYzz_j7uzJ1V2-sfdawn64aU",
                contentDescription = "Foto do usuário",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Jessica Brown",
                    color = textPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "@jessicab",
                    color = textSecondary,
                    fontSize = 14.sp
                )
            }
        }

        Text(
            text = "Estou começando um desafio de 30 dias para aprender um novo idioma. Sempre quis falar espanhol fluentemente e acho que esta é uma ótima maneira de começar. Vou usar um aplicativo de aprendizado de idiomas e praticar com um falante nativo online. Desejem-me sorte!",
            color = textPrimary,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "2 dias atrás",
            color = textSecondary,
            fontSize = 12.sp
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* Curtir post */ },
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Curtir",
                    tint = likeColor
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "12",
                color = likeColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun CommentsHeader() {
    Text(
        text = "Comentários",
        color = textPrimary,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    )
}

@Composable
private fun CommentItem(
    comment: Comment,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardBackground)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = comment.userImage,
            contentDescription = "Foto do usuário",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .background(cardBackground, RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = comment.userName,
                            color = textPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = comment.timeAgo,
                            color = textSecondary,
                            fontSize = 12.sp
                        )
                    }

                    Text(
                        text = comment.comment,
                        color = textPrimary,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CommentInput() {
    var commentText by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAuHqiwM5FQWy-LN9Za7r604KD3cGRzRGwVz_pKt4b2OJP7E7FLQtSdmaSLSPIej-_E76Gln5tpzlmPurv-sTMS1wqwTmo6MIpJ3-J72ssZYOBGTHbzDoaEu5wHOIl9ksQGUxcm2wqNevQCpQfwezOpDileTEfp6DqbgLW3SPFgaKBXDaGOOBqa6cO6r1PNCLigfF-DsrrBLy5e7Xsu8V6AwHBqRMFK0gDpmQAQr9HeBMXzviNkOmIFl2K2j1vF_qBo_gP_aUhTj9M",
            contentDescription = "Seu perfil",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = commentText,
            onValueChange = { commentText = it },
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .background(backgroundColor, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            decorationBox = { innerTextField ->
                if (commentText.text.isEmpty()) {
                    Text(
                        "Adicionar um comentário...",
                        color = textSecondary,
                        fontSize = 14.sp
                    )
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.width(8.dp))

        PrimaryButton(
            onClick = { /* Postar comentário */ },
            shape = RoundedCornerShape(8.dp),
            text = "Postar"
        )
    }
}

@Preview
@Composable
fun CommunityPostScreenPreview() {
    CommunityPostScreen()
}