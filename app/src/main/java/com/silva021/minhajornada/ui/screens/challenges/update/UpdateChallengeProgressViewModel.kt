package com.silva021.minhajornada.ui.screens.challenges.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.usecases.GetChallengeByIdUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UpdateChallengeProgressViewModel(
    private val getChallengeById: GetChallengeByIdUseCase,
) : ViewModel() {
    private var _uiState = MutableStateFlow<UpdateChallengeProgressUiState>(
        value = UpdateChallengeProgressUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    fun loadChallengeById(id: String) {
        viewModelScope.launch {
            getChallengeById(id).onSuccess { challenge ->
                _uiState.value = UpdateChallengeProgressUiState.Idle(challenge)
            }.onFailure { error ->
                _uiState.value =
                    UpdateChallengeProgressUiState.Error(error.message ?: "Unknown error")
            }
        }
    }

    fun updateProgress(challenge: Challenge) {
        viewModelScope.launch {
            _uiState.value = UpdateChallengeProgressUiState.Loading
            delay(6000)
            _uiState.value = UpdateChallengeProgressUiState.Success("Progress updated successfully")
        }
    }

}

sealed class UpdateChallengeProgressUiState {
    object Loading : UpdateChallengeProgressUiState()
    data class Success(val message: String) : UpdateChallengeProgressUiState()
    data class Error(val message: String) : UpdateChallengeProgressUiState()
    data class Idle(val challenge: Challenge) : UpdateChallengeProgressUiState()
}