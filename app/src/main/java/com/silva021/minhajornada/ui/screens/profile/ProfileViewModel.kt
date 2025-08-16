package com.silva021.minhajornada.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.domain.usecases.GetMyProfileUseCase
import com.silva021.minhajornada.domain.usecases.LogoutUserUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfile: GetMyProfileUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()


    fun fetchProfile() {
        if (_uiState.value.isSuccess().not()) {
            viewModelScope.launch {
                _uiState.value = ProfileUiState.Loading
                getProfile().onSuccess {
                    _uiState.value = ProfileUiState.Success(it)
                }.onFailure {
                    _uiState.value = ProfileUiState.Error(
                        message = it.message ?: "Erro ao carregar perfil"
                    )
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            logoutUserUseCase.invoke().onSuccess {
                _eventFlow.emit(NavigationEvent.NavigateToLogin)
            }.onFailure {
                _uiState.value = ProfileUiState.Error(
                    message = it.message ?: "Erro ao realizar logout"
                )
            }
        }
    }
}

sealed class ProfileUiState {
    data class Success(val profile: Profile) : ProfileUiState()
    object Loading : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()

    fun isSuccess() = this is Success
}

sealed class NavigationEvent {
    data object NavigateToLogin : NavigationEvent()
}