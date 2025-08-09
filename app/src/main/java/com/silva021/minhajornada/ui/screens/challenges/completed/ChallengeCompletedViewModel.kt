package com.silva021.minhajornada.ui.screens.challenges.completed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.ChallengeResult
import com.silva021.minhajornada.domain.usecases.CompleteChallengeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChallengeCompletedViewModel(
    private val completeChallengeUseCase: CompleteChallengeUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ChallengeCompletedUiState>(
        value = ChallengeCompletedUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    fun completeChallenge(challengeId: String) {
        viewModelScope.launch {
            completeChallengeUseCase.invoke(challengeId).onSuccess {
                _uiState.value = ChallengeCompletedUiState.ChallengeCompleted(it.first, it.second)
            }.onFailure {
                _uiState.value = ChallengeCompletedUiState.Error(
                    message = it.message ?: "Failed to complete challenge"
                )
            }
        }
    }

}

sealed class ChallengeCompletedUiState {
    object Loading : ChallengeCompletedUiState()
    data class ChallengeCompleted(
        val challenge: Challenge,
        val result: ChallengeResult
    ) : ChallengeCompletedUiState()
    data class Error(val message: String) : ChallengeCompletedUiState()
}