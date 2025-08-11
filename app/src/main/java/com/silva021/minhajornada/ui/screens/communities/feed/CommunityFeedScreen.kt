package com.silva021.minhajornada.ui.screens.communities.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.InputArea
import com.silva021.minhajornada.ui.components.NewPostAreaSkeleton
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import com.silva021.minhajornada.ui.theme.Palette
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunityFeedScreen(
    viewModel: CommunityFeedViewModel = koinViewModel(),
    communityId: String,
    onBackPressed: () -> Unit,
    onClickPost: (Post) -> Unit,
    onEditPost: (Post) -> Unit,
    onNavigateCommunities: () -> Unit
) {
    val communityState by viewModel.communityState.collectAsState()
    val feedState by viewModel.feedState.collectAsState()
    val userState by viewModel.userState.collectAsState()
    var inputText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadCommunityScreen(communityId)

        viewModel.eventFlow.collect { event ->
            when (event) {
                NavigationEvent.NavigateToCommunities -> {
                    onNavigateCommunities.invoke()
                }
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
    ) {
        Header(
            "Feed da Comunidade", onBackPressed = onBackPressed
        )

        when (val state = communityState) {
            is CommunityHeaderUiState.Loading -> CommunityHeaderSkeleton()
            is CommunityHeaderUiState.Error -> CommunityHeaderSkeleton()
            is CommunityHeaderUiState.Success -> CommunityHeader(
                state.community,
                onLeaveCommunity = {
                    viewModel.leaveCommunity(state.community.id)
                }
            )
        }

        when (val state = feedState) {
            is CommunityFeedUiState.Loading -> LoadingScreen()

            is CommunityFeedUiState.Error -> ErrorScreen(
                onRetry = {}
            )

            is CommunityFeedUiState.Success -> {
                if (state.posts.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(state.posts) {
                            PostItem(
                                post = it,
                                onPostClick = {
                                    onClickPost.invoke(it)
                                },
                                onEditPost = {
                                    onEditPost.invoke(it)
                                }
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Nenhuma postagem ainda",
                            color = Palette.textSecondary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        when (val state = userState) {
            is UserInfoUiState.Loading -> NewPostAreaSkeleton()
            is UserInfoUiState.Error -> {
                InputArea(
                    placeholder = "Escreva algo...",
                    profilePictureUrl = null,
                    postText = inputText,
                    onPostTextChange = { inputText = it },
                    onClick = {
                        viewModel.createPost(
                            communityId,
                            inputText
                        ).also {
                            inputText = ""
                        }
                    }
                )
            }
            is UserInfoUiState.Success -> {
                InputArea(
                    placeholder = "Escreva algo...",
                    profilePictureUrl = state.profile.profilePictureUrl,
                    postText = inputText,
                    onPostTextChange = { inputText = it },
                    onClick = {
                        viewModel.createPost(
                            communityId,
                            inputText
                        ).also {
                            inputText = ""
                        }
                    }
                )
            }
        }
    }
}