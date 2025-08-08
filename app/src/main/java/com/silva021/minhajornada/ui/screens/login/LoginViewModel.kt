package com.silva021.minhajornada.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginScreenState>(LoginScreenState.Idle)
    val uiState: StateFlow<LoginScreenState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginScreenState.Loading

            loginUseCase.invoke(
                email = email,
                password = password
            ).onSuccess {
                _uiState.value = LoginScreenState.Success
            }.onFailure { messageError ->
                _uiState.value = LoginScreenState.Error(messageError.message ?: "Não identificamos o erro, entre em contato com o suporte")
            }
        }
    }
}

sealed class LoginScreenState {
    object Idle : LoginScreenState()
    object Success : LoginScreenState()
    object Loading : LoginScreenState()
    data class Error(val message: String) : LoginScreenState()
}