package com.silva021.minhajornada.ui.screens.challenges.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateChallengeScreen(
    viewModel: CreateChallengeViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is CreateChallengeUiState.Idle, is CreateChallengeUiState.Error -> {
            CreateChallengeContent(
                onBackPressed = onBackPressed,
                onSave = {
                    viewModel.createChallenge(it)
                }
            )
        }
        is CreateChallengeUiState.Loading -> LoadingScreen()
        is CreateChallengeUiState.Success -> {
            LaunchedEffect(Unit) {
                onBackPressed.invoke()
            }
        }
    }
}