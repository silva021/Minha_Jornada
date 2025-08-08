package com.silva021.minhajornada.ui.screens.challenges.reminders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.usecases.DeleteReminderUseCase
import com.silva021.minhajornada.domain.usecases.GetChallengeByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RemindersViewModel(
    private val challengeById: GetChallengeByIdUseCase,
    private val deleteReminder: DeleteReminderUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RemindersUiState>(RemindersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadChallengeById(id: String) {
        viewModelScope.launch {
            _uiState.value = RemindersUiState.Loading
            challengeById(id).onSuccess { challenge ->
                _uiState.value = RemindersUiState.Success(challenge)
            }.onFailure { error ->
                _uiState.value = RemindersUiState.Error(error.message ?: "Unknown error")
            }
        }
    }

    fun deleteReminder(reminderId: String, challengeId: String) {
        viewModelScope.launch {
            deleteReminder.invoke(
                reminderId = reminderId,
                challengeId = challengeId
            ).onSuccess { challenge ->
                loadChallengeById(challengeId)
            }.onFailure { error ->
                _uiState.value = RemindersUiState.Error(error.message ?: "Unknown error")
            }
        }
    }
}

sealed class RemindersUiState {
    object Loading : RemindersUiState()
    data class Success(val challenge: Challenge) : RemindersUiState()
    data class Error(val message: String) : RemindersUiState()
}