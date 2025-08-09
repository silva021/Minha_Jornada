package com.silva021.minhajornada.ui.screens.challenges.update

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.domain.model.ChallengeResult
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateChallengeProgressScreen(
    viewModel: UpdateChallengeProgressViewModel = koinViewModel(),
    challengeId: String,
    onCompletedDay: (ChallengeResult) -> Unit,
    onCompleteChallenge: (ChallengeResult) -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadChallengeById(challengeId)
    }

    when (val state = uiState) {
        is UpdateChallengeProgressUiState.Loading -> {
            LoadingScreen()
        }

        is UpdateChallengeProgressUiState.Success -> {
        }

        is UpdateChallengeProgressUiState.Error -> {
            ErrorScreen(
                onRetry = {
                    viewModel.loadChallengeById(challengeId)
                },
            )
        }

        is UpdateChallengeProgressUiState.Idle -> {
            UpdateChallengeProgressContent(
                state.challenge,
                onCompletedDay = onCompletedDay,
                onCompleteChallenge = onCompleteChallenge,
                onBackPressed = onBackPressed,
            )
        }
    }

}