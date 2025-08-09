package com.silva021.minhajornada.ui.screens.explorer.challengedetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExplorerChallengeDetailsScreen(
    viewModel: ExplorerChallengeDetailsViewModel = koinViewModel(),
    challengeId: String,
    navigateToChallengeScreen: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPublicChallengeById(challengeId)

        viewModel.eventFlow.collect { event ->
            when (event) {
                is NavigationEvent.NavigateToChallengeScreen -> {
                    navigateToChallengeScreen.invoke()
                }
            }
        }
    }

    when (val state = uiState.value) {
        is ExplorerChallengeDetailsUiState.Loading -> {
            LoadingScreen()
        }

        is ExplorerChallengeDetailsUiState.Error -> {
            ErrorScreen(
                onRetry = {
                    viewModel.fetchPublicChallengeById(challengeId)
                }
            )
        }

        is ExplorerChallengeDetailsUiState.Idle -> {
            ExplorerChallengeDetailsContent(
                challenge = state.challenge,
                onBackPressed = onBackPressed,
                acceptChallenge = { challenge ->
                    viewModel.acceptChallenge(challenge)
                }
            )
        }
    }
}
