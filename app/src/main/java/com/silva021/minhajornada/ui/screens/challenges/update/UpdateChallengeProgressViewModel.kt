package com.silva021.minhajornada.ui.screens.challenges.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.extension.allCheckInDone
import com.silva021.minhajornada.domain.extension.currentChallengeDay
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.CheckIn
import com.silva021.minhajornada.domain.usecases.CreateCheckInUseCase
import com.silva021.minhajornada.domain.usecases.GetChallengeByIdUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UpdateChallengeProgressViewModel(
    private val getChallengeById: GetChallengeByIdUseCase,
    private val createCheckIn: CreateCheckInUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow<UpdateChallengeProgressUiState>(
        value = UpdateChallengeProgressUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()

    fun loadChallengeById(id: String) {
        viewModelScope.launch {
            getChallengeById(id).onSuccess { challenge ->
                if (challenge.allCheckInDone()) {
                    _eventFlow.emit(
                        NavigationEvent.NavigateToCompleteChallenge(challengeId = challenge.id)
                    )
                    return@onSuccess
                }

                _uiState.value = UpdateChallengeProgressUiState.Idle(
                    challenge = challenge,
                    currentCheckIn = challenge.checkins.find { it.day == challenge.currentChallengeDay() }
                )
            }.onFailure { error ->
                _uiState.value =
                    UpdateChallengeProgressUiState.Error(error.message ?: "Unknown error")
            }
        }
    }

    fun updateProgress(
        challenge: Challenge,
        checkIn: CheckIn
    ) {
        viewModelScope.launch {
            _uiState.value = UpdateChallengeProgressUiState.Loading
            createCheckIn.invoke(
                challengeId = challenge.id,
                checkIn = checkIn
            ).onSuccess {
                loadChallengeById(challenge.id)
            }.onFailure { error ->
                _uiState.value = UpdateChallengeProgressUiState.Error(
                    error.message ?: "Failed to update progress"
                )
            }
        }
    }

}

sealed class UpdateChallengeProgressUiState {
    object Loading : UpdateChallengeProgressUiState()
    data class Error(val message: String) : UpdateChallengeProgressUiState()
    data class Idle(
        val challenge: Challenge,
        val currentCheckIn: CheckIn? = null,
    ) : UpdateChallengeProgressUiState()
}

sealed class NavigationEvent {
    data class NavigateToCompleteChallenge(
        val challengeId: String
    ) : NavigationEvent()
}