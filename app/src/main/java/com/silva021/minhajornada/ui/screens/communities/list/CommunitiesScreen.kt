package com.silva021.minhajornada.ui.screens.communities.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.domain.model.Challenges
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitiesScreen(
    viewModel: CommunitiesViewModel = koinViewModel(),
    onCommunityClick: (Community) -> Unit,
    onMineCommunityClick: (Community) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val category by viewModel.selectedCategory.collectAsState()

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
            CommunitiesContent(
                state.communities,
                onCommunityClick = onCommunityClick,
                onMineCommunityClick = onMineCommunityClick,
                selectedCategory = category,
                onCategoryClick = { category ->
                    viewModel.onCategorySelected(category)
                }
            )
        }
    }
}

sealed class ChallengesUiState {
    data class Success(val data: Challenges) : ChallengesUiState()
    object Loading : ChallengesUiState()
    data class Error(val message: String) : ChallengesUiState()
}