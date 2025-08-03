package com.silva021.minhajornada.ui.screens.communities.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import com.silva021.minhajornada.ui.theme.Palette
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunityFeedScreen(
    viewModel: CommunityFeedViewModel = koinViewModel(),
    communityId: String,
    onBackPressed: () -> Unit,
    onClickPostItem: (Post) -> Unit,
) {
    val communityState by viewModel.communityState.collectAsState()
    val feedState by viewModel.feedState.collectAsState()
    val userState by viewModel.userState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCommunityScreen(communityId)
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
            is CommunityHeaderUiState.Error -> {}
            is CommunityHeaderUiState.Success -> CommunityHeader(state.community)
        }

        when (val state = feedState) {
            is CommunityFeedUiState.Loading -> LoadingScreen()

            is CommunityFeedUiState.Error -> ErrorScreen(
                onRetry = {}
            )

            is CommunityFeedUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(state.posts) {
                        PostItem(it) {
                            onClickPostItem(it)
                        }
                    }
                }
            }
        }

        when (val state = userState) {
            is UserInfoUiState.Loading -> NewPostAreaSkeleton()
            is UserInfoUiState.Error -> {}
            is UserInfoUiState.Success -> {
                NewPostArea(state.profile)
            }
        }
    }
}