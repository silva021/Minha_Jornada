package com.silva021.minhajornada.ui.screens.login.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.domain.usecases.CreateProfileUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    val createProfile: CreateProfileUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterScreenState>(RegisterScreenState.Idle)
    val uiState: StateFlow<RegisterScreenState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()

    fun register(profile: Profile, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterScreenState.Loading
            createProfile.invoke(
                profile = profile,
                password = password
            ).onSuccess {
                _eventFlow.emit(NavigationEvent.NavigateToChallengeScreen)
            }.onFailure { messageError ->
                _uiState.value = RegisterScreenState.Error(messageError.message ?: "Não identificamos o erro, entre em contato com o suporte")
            }
        }
    }
}

sealed class RegisterScreenState {
    object Idle : RegisterScreenState()
    object Loading : RegisterScreenState()
    data class Error(val message: String) : RegisterScreenState()
}

sealed class NavigationEvent {
    data object NavigateToChallengeScreen : NavigationEvent()
}