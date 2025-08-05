package com.silva021.minhajornada.ui.screens.challenges.reminders

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.Reminder
import com.silva021.minhajornada.domain.usecases.GetChallengeByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RemindersViewModel(
    private val challengeById: GetChallengeByIdUseCase,
) : ViewModel() {

    private val _reminders = mutableStateListOf<Reminder>()
    val reminders: List<Reminder> = _reminders

    private val _uiState = MutableStateFlow<RemindersUiState>(RemindersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun addReminder(reminder: Reminder) {
        _reminders.add(reminder)
    }

    fun updateReminder(updated: Reminder) {
        val index = _reminders.indexOfFirst { it.id == updated.id }
        if (index != -1) _reminders[index] = updated
    }

    fun deleteReminder(id: String) {
        _reminders.removeAll { it.id == id }
    }

    fun getReminder(id: String): Reminder? {
        return _reminders.find { it.id == id }
    }

    fun loadChallengeById(id: Int) {
        viewModelScope.launch {
            _uiState.value = RemindersUiState.Loading
            challengeById(id).onSuccess { challenge ->
                _reminders.addAll(challenge.reminders)
                _uiState.value = RemindersUiState.Success(challenge)

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