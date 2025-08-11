package com.silva021.minhajornada.ui.screens.communities.post.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.data.dto.PostDTO
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.components.CustomTextField
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.SecondButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPostContent(
    post: Post,
    onUpdatePost: (String) -> Unit,
    onDeletePost: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val originalPostText = post.content
    var postText by remember {
        mutableStateOf(post.content)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
    ) {
        Header(
            "Editar Post",
            onBackPressed = onBackPressed
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Publicação",
                color = textSecondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            CustomTextField(
                value = postText,
                onValueChange = { postText = it },
                placeholder = "No que você está pensando?",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (postText != originalPostText) {
                    SecondButton(
                        onClick = {
                            postText = originalPostText
                        },
                        text = "Cancelar",
                    )
                } else {
                    Button(
                        onClick = onDeletePost,
                        modifier = Modifier
                            .height(48.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Palette.errorColor,
                            contentColor = textPrimary
                        )
                    ) {
                        Text(
                            text = "Excluir",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                PrimaryButton(
                    onClick = { onUpdatePost.invoke(postText) },
                    text = "Atualizar",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditPostContentPreview() {
    MaterialTheme {
        EditPostContent(
            DatabaseFake.postList.first(),
            onDeletePost = {},
            onUpdatePost = {},
            onBackPressed = { /* No action */ }
        )
    }
}