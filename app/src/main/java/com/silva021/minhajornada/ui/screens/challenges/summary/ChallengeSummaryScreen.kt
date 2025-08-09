package com.silva021.minhajornada.ui.screens.challenges.summary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChallengeSummaryScreen(
    viewModel: ChallengeSummaryViewModel = koinViewModel(),
    challengeId: String,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getChallenge(challengeId)
    }

    when (val state = uiState) {
        is ChallengeSummaryUiState.Loading -> LoadingScreen()
        is ChallengeSummaryUiState.Error -> ErrorScreen(
            onRetry = { viewModel.getChallenge(challengeId) },
        )
        is ChallengeSummaryUiState.Success -> {
            ChallengeSummaryContent(
                challenge = state.challenge,
                onBackPressed = onBackPressed,
            )
        }
    }
}