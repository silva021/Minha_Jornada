package com.silva021.minhajornada.ui.screens.profile

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.silva021.minhajornada.ui.screens.defaults.error.ErrorScreen
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onContactUsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onRemindersClick: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchProfile()

        viewModel.eventFlow.collect { event ->
            when (event) {
                is NavigationEvent.NavigateToLogin -> {
                    onNavigateToLogin.invoke()
                }
                is NavigationEvent.ShowSnackbar -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Long
                        )
                    }
                }
            }
        }
    }

    when (val state = uiState) {
        is ProfileUiState.Loading -> LoadingScreen()
        is ProfileUiState.Error -> ErrorScreen(
            onRetry = { viewModel.fetchProfile() }
        )
        is ProfileUiState.Success -> {
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
            ) { _ ->
                ProfileContent(
                    profile = state.profile,
                    onEditProfileClick = onEditProfileClick,
                    onContactUsClick = onContactUsClick,
                    onHelpClick = onHelpClick,
                    onRemindersClick = onRemindersClick,
                    onDeleteAccount = { viewModel.deleteAccount() },
                    onLogout = { viewModel.logout() }
                )
            }
        }
    }
}