package com.silva021.minhajornada.ui.screens.explorer.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.domain.model.PublicChallenge
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExplorerScreen(
    viewModel: ExplorerViewModel = koinViewModel(),
    onChallengeClick: (PublicChallenge) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val category by viewModel.selectedCategory.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPublicChallenges()
    }

    when (val state = uiState) {
        is ExplorerUiState.Loading -> LoadingScreen()
        is ExplorerUiState.Error -> ErrorScreen(
            onRetry = { viewModel.fetchPublicChallenges() }
        )

        is ExplorerUiState.Success -> ExplorerContent(
            publicChallenges = state.list,
            onChallengeClick = onChallengeClick,
            selectedCategory = category,
            onCategoryClick = { category ->
                viewModel.onCategorySelected(category)
            }
        )
    }
}