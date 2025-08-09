package com.silva021.minhajornada.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.usecases.LoginUseCase
import com.silva021.minhajornada.ui.screens.login.signup.NavigationEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginScreenState>(LoginScreenState.Idle)
    val uiState: StateFlow<LoginScreenState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginScreenState.Loading

            loginUseCase.invoke(
                email = email,
                password = password
            ).onSuccess {
                _eventFlow.emit(NavigationEvent.NavigateToChallengeScreen)
            }.onFailure { messageError ->
                _uiState.value = LoginScreenState.Error(messageError.message ?: "Não identificamos o erro, entre em contato com o suporte")
            }
        }
    }
}

sealed class LoginScreenState {
    object Idle : LoginScreenState()
    object Loading : LoginScreenState()
    data class Error(val message: String) : LoginScreenState()
}