package com.silva021.minhajornada.ui.screens.challenges.completed

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.silva021.minhajornada.domain.model.ChallengeResult
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChallengeCompletedScreen(
    viewModel: ChallengeCompletedViewModel = koinViewModel(),
    challengeId: String,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.completeChallenge(challengeId)
    }

    when (val state = uiState) {
        is ChallengeCompletedUiState.Loading -> {
            LoadingScreen()
        }
        is ChallengeCompletedUiState.ChallengeCompleted -> {
            ChallengeCompletedContent(
                challenge = state.challenge,
                result = state.result,
                onBackPressed = onBackPressed
            )
        }
        is ChallengeCompletedUiState.Error -> {
            ErrorScreen(
                onRetry = {
                    viewModel.completeChallenge(challengeId)
                }
            )
        }
    }
}
