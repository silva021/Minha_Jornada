package com.silva021.minhajornada.ui.screens.communities.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.InputArea
import com.silva021.minhajornada.ui.components.NewPostAreaSkeleton
import com.silva021.minhajornada.ui.screens.communities.feed.UserInfoUiState
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunityPostScreen(
    viewModel: CommunityPostViewModel = koinViewModel(),
    postId: String,
    onBackPressed: () -> Unit,
) {
    val postState by viewModel.postState.collectAsState()
    val userState by viewModel.userState.collectAsState()
    val commentsState by viewModel.commentsState.collectAsState()
    var inputText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadPostInfo(postId)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Header("Postagem", onBackPressed)

        when (val state = postState) {
            is CommunityPostUiState.Loading -> LoadingScreen()
            is CommunityPostUiState.Error -> {}
            is CommunityPostUiState.Success -> PostContent(state.post)
        }

        when (val state = commentsState) {
            is CommunityCommentsUiState.Loading -> {}
            is CommunityCommentsUiState.Error -> ErrorScreen(
                onRetry = { viewModel.loadPostInfo(postId) }
            )
            is CommunityCommentsUiState.Success -> {
                CommentsHeader()

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(state.comments) { comment ->
                        CommentItem(comment)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }

        when (val state = userState) {
            is UserInfoUiState.Loading -> NewPostAreaSkeleton()
            is UserInfoUiState.Error -> InputArea(
                placeholder = "Adicionar um comentário...",
                profilePictureUrl = null,
                postText = inputText,
                onPostTextChange = { inputText = it }
            )
            is UserInfoUiState.Success -> {
                InputArea(
                    placeholder = "Adicionar um comentário...",
                    profilePictureUrl = state.profile.profilePictureUrl,
                    postText = inputText,
                    onPostTextChange = { inputText = it }
                )
            }
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