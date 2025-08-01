package com.silva021.minhajornada.ui.screens.communities

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.domain.model.Challenges
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitiesScreen(
    viewModel: CommunitiesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCommunities()
    }

    when (val state = uiState) {
        is CommunitiesUiState.Loading -> LoadingScreen()
        is CommunitiesUiState.Error -> ErrorScreen(
            onRetry = { viewModel.fetchCommunities() },
            onContactSupport = {}
        )
        is CommunitiesUiState.Success -> {
            CommunitiesContent(state.communities)
        }
    }
}

sealed class ChallengesUiState {
    data class Success(val data: Challenges) : ChallengesUiState()
    object Loading : ChallengesUiState()
    data class Error(val message: String) : ChallengesUiState()
}