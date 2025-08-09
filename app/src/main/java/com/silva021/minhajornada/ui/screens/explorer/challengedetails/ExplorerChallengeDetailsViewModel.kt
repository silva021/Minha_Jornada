package com.silva021.minhajornada.ui.screens.explorer.challengedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.PublicChallenge
import com.silva021.minhajornada.domain.usecases.AcceptPublicChallengeUseCase
import com.silva021.minhajornada.domain.usecases.GetPublicChallengeByIdUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExplorerChallengeDetailsViewModel(
    private val getPublicChallengeById: GetPublicChallengeByIdUseCase,
    private val acceptChallenge: AcceptPublicChallengeUseCase,
) : ViewModel() {
    private var _uiState =
        MutableStateFlow<ExplorerChallengeDetailsUiState>(ExplorerChallengeDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()

    fun fetchPublicChallengeById(id: String) {
        viewModelScope.launch {
            _uiState.value = ExplorerChallengeDetailsUiState.Loading
            getPublicChallengeById(id)
                .onSuccess { challenge ->
                    _uiState.value = ExplorerChallengeDetailsUiState.Idle(challenge)
                }
                .onFailure { exception ->
                    _uiState.value =
                        ExplorerChallengeDetailsUiState.Error(exception.message ?: "Unknown error")
                }
        }
    }

    fun acceptChallenge(challenge: PublicChallenge) {
        viewModelScope.launch {
            _uiState.value = ExplorerChallengeDetailsUiState.Loading
            acceptChallenge.invoke(challenge).onSuccess {
                _eventFlow.emit(NavigationEvent.NavigateToChallengeScreen)
            }.onFailure {
                _uiState.value = ExplorerChallengeDetailsUiState.Error(it.message ?: "Failed to accept challenge")
            }
        }
    }
}

sealed class ExplorerChallengeDetailsUiState {
    object Loading : ExplorerChallengeDetailsUiState()
    data class Idle(val challenge: PublicChallenge) : ExplorerChallengeDetailsUiState()
    data class Error(val message: String) : ExplorerChallengeDetailsUiState()
}

sealed class NavigationEvent {
    data object NavigateToChallengeScreen : NavigationEvent()
}