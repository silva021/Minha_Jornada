package com.silva021.minhajornada.ui.screens.profile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import androidx.compose.runtime.getValue

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onContactUsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onRemindersClick: () -> Unit
    onRemindersClick: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchProfile()

        viewModel.eventFlow.collect { event ->
            when (event) {
                is NavigationEvent.NavigateToLogin -> {
                    onNavigateToLogin.invoke()
                }
            }
        }
    }

    when (val state = uiState) {
        is ProfileUiState.Loading -> LoadingScreen()
        is ProfileUiState.Error -> ErrorScreen(
            onRetry = { viewModel.fetchProfile() }
        )
        is ProfileUiState.Success -> ProfileContent(
            profile = state.profile,
            onEditProfileClick = onEditProfileClick,
            onContactUsClick = onContactUsClick,
            onHelpClick = onHelpClick,
            onRemindersClick = onRemindersClick
            onRemindersClick = onRemindersClick,
            onLogout = { viewModel.logout() }
        )
    }
}