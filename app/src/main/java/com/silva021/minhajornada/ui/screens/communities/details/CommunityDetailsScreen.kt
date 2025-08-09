package com.silva021.minhajornada.ui.screens.communities.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunityDetailsScreen(
    viewModel: CommunityDetailsViewModel = koinViewModel(),
    navigateToCommunity: (String) -> Unit,
    communityId: String,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCommunityDetails(communityId)

        viewModel.eventFlow.collect {
            when (it) {
                NavigationEvent.NavigateToCommunityFeed -> {
                    navigateToCommunity.invoke(communityId)
                }
            }
        }
    }

    when (val state = uiState) {
        is CommunityDetailsUiState.Loading -> {
            LoadingScreen()
        }
        is CommunityDetailsUiState.Error -> {
            ErrorScreen(
                onRetry = { viewModel.fetchCommunityDetails(communityId) },
            )
        }
        is CommunityDetailsUiState.Success -> {
            CommunityDetailsContent(
                community = state.community,
                joinCommunityClick = { viewModel.joinCommunity(communityId) },
                onBackPressed = onBackPressed,
            )
        }
    }
}
