package com.silva021.minhajornada.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.silva021.minhajornada.ui.screens.defaults.loading.LoadingScreen
import com.silva021.minhajornada.ui.screens.login.signup.NavigationEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onSignUp: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect {
            when(it) {
                NavigationEvent.NavigateToChallengeScreen -> {
                    onLoginSuccess.invoke()
                }
            }
        }
    }

    when (val state = uiState.value) {
        is LoginScreenState.Loading -> {
            LoadingScreen()
        }
        is LoginScreenState.Idle, is LoginScreenState.Error -> {
            val errorMessage = if (state is LoginScreenState.Error) state.message else null
            LoginContent(
                errorMessage = errorMessage,
                onLogin = { email, password ->
                    viewModel.login(email, password)
                },
                onSignUp = onSignUp
            )
        }
    }
}