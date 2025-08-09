package com.silva021.minhajornada.ui.screens.challenges.mine

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengesScreen(
    viewModel: ChallengesViewModel = koinViewModel(),
    onCreateChallenge: () -> Unit,
    onUpdateChallengeProgress: (String) -> Unit,
    onSummaryChallengeClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getChallenges()
    }

    when (val state = uiState) {
        is ChallengesUiState.Error -> {
            ErrorScreen(
                onRetry = {
                    viewModel.getChallenges()
                },
                onContactSupport = {},
            )
        }
        is ChallengesUiState.Loading -> {
            LoadingScreen()
        }
        is ChallengesUiState.Success -> {
            ChallengesContent(
                challenges = state.challenges,
                onCreateChallenge = onCreateChallenge,
                onUpdateChallengeProgress = onUpdateChallengeProgress,
                onSummaryChallengeClick = onSummaryChallengeClick
            )
        }
    }
}
