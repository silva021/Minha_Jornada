package com.silva021.minhajornada.ui.screens.communities.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreHoriz
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun CommunityFeedScreen(
    onBackPressed: () -> Unit,
    onClickPostItem: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header(
            "Feed da Comunidade", onBackPressed = onBackPressed
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                CommunityHeader()
            }

            val postList = listOf(
                Post(
                    userImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuDtOZOGqPBmoMIxOJODpUF6L55FFzYzDm-7hdl2NAIn44H2ViOQAGcW_9BLQ1JXgaCtWCA4Faq3I-uU4MF6pDd9ixBDalXweY9q1Tz1CBZe5F0dCHySwJjn21fBCZ-dHBU_lDUbFSItE21a0vIT-wO9bdYw1hPv5ej6XyBzb5sVHP3Lh0hi59j48AntkhtNH0ADIm--kflWd8AZa-ZA7GmULHesWk8EcM98tMB3ebeLOk5LiaU9-dgp-qwq5ouQj3JP4v1mc73v1L8",
                    userName = "Sarah Miller",
                    timeAgo = "2d",
                    content = "Acabei de completar minha corrida de 5km! Me sentindo ótima e cheia de energia para o dia. Quem mais está alcançando seus objetivos fitness hoje?",
                    likes = 23,
                    comments = 5
                ),

                Post(
                    userImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuD38031a9CUlzl_YS565weCOqjozwjPs_D8QhTpnQNCNbreXCZXtJmMNmIYPu0l8vmmXMGS3EMcPwJG-tlMUr_-Y0y3qxdtsG0HyurbO0MBouSoZDdD9dX7QgSFqDi5nyeq3Hrioaln2cG345utbSVfOeEuzmpREIlRnlAJXNB_VTsajQcFh-KuG4hX_vWA1CaeRpIXU-1j-ZH20kzY2vxMbAvV1mpjvFXMgD82P80FTne_XTXi1yd1pVPBpE7GXZ-zHFKHnG1yHqo",
                    userName = "Mark Thompson",
                    timeAgo = "1sem",
                    content = "Estou tentando incluir mais treino de força na minha rotina. Alguma dica para iniciantes?",
                    likes = 15,
                    comments = 3
                ),
                Post(
                    userImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCbc0xLKqOdwb-Vi-QOdbZm9ShGVL6V1BcoLgrMCJ3aRNaobT2QFOCFnWF0fp1EdXFif8_exZfOcq5ANfbEtGZ0p1g9KqcNdtOvcbY_uq7jj9I4xYcnoZ4TZr5SEyfXs8ZdKwJ7bWwBwORGdm82ELnptKa1VcaLPNxIuSVLa5FQj6DGoPeTlEPTmiGbswtKYmBST5fc7gxGBIJB4rN1miXSXWb1BJfCvVrJEQtSzC6eoQAJzLD5A9yAUeUeuDEKQUo-oht2W1yKdQ8",
                    userName = "Emily Carter",
                    timeAgo = "3sem",
                    content = "Alguém animado para uma trilha no final de semana? Vamos explorar novas trilhas e aproveitar o ar livre juntos!",
                    likes = 30,
                    comments = 8
                )
            )

            items(postList) {
                PostItem(it) {
                    onClickPostItem()
                }
            }
        }

        // Área de novo post (fixa na parte inferior)
        NewPostArea()
    }
}

@Composable
private fun CommunityHeader() {
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
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuD9-tV5n5JOQL4OJtNs4OgJv08Y4JvKstcFqQimD4MZw7Uk4-XFDOk93zlmDd8sVHSYtAAsXlPw-kj_N9oJFIzr6cmoGRUQ63pPPaEINbTUxf_MxHDb-kRyj4uRRr-j_0_CfRQCyvJMdzdk9pye5XoJisAAku8DLhn-2jpgXnis8U1tZcjYIISzJAF1VIk5U2nuC193dgim_JWGWhKVnA_Pb--Uf1zrEYn8ZH6FJO2aJeQ5NlGd1qJt7lmocn79iwsVdcdTyetMDI8",
                contentDescription = "Foto da comunidade",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Viciados em Fitness",
                    color = textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "123 membros", color = textSecondary, fontSize = 14.sp
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

@Composable
private fun PostItem(
    post: Post,
    onPostClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Palette.cardBackground, RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable {
                onPostClick.invoke()
            }) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = post.userImage,
                contentDescription = "Foto do usuário",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = post.userName,
                    color = textPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = post.timeAgo, color = textSecondary, fontSize = 12.sp
                )
            }
        }

        Text(
            text = post.content,
            color = textPrimary,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Curtir",
                        tint = textSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = post.likes.toString(), color = textSecondary, fontSize = 14.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ChatBubbleOutline,
                        contentDescription = "Comentar",
                        tint = textSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = post.comments.toString(), color = textSecondary, fontSize = 14.sp
                    )
                }
            }

            IconButton(
                onClick = { /* Mostrar opções */ }, modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = "Mais opções",
                    tint = textSecondary
                )
            }
        }
    }
}

@Composable
private fun NewPostArea() {
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
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAXjk17jzzzp3okto8YCUGaAjfMMcCN6KMAM9AOhegn-UnaeKEoSki8P0dQJH8Dlh7DYeeAci9T9bP6_SlPtQuVjSSwdi6ZkreCUiVHd6T2JeefSWC_xAemoj2y5b3ju4GaDpCa64qlF87sGjWTMt22OhP49SaNqOdDlhUF-RUnSpA076Sa8K5PQ8tsUZdnhkayWXDFLb7vBWNJRe43jAkNYZdD0GH4L5iHgUK6Rl9WR7NJo1ni5BkPoYXShM4MyJ_zq0_Q-c1Vs_4",
                contentDescription = "Seu perfil",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

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

@Composable
@Preview
fun PreviewCommunityFeedScreen() {
    CommunityFeedScreen(
        onBackPressed = {},
        onClickPostItem = {}
    )
}