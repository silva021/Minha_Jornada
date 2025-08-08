package com.silva021.minhajornada.ui.screens.challenges.reminders.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Reminder
import com.silva021.minhajornada.domain.usecases.CreateReminderUseCase
import com.silva021.minhajornada.domain.usecases.GetReminderByIdUseCase
import com.silva021.minhajornada.domain.usecases.UpdateReminderUseCase
import com.silva021.minhajornada.ui.screens.challenges.reminders.create.CreateRemindersUiState.Error
import com.silva021.minhajornada.ui.screens.challenges.reminders.create.CreateRemindersUiState.Loading
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateReminderViewModel(
    private val createReminder: CreateReminderUseCase,
    private val updateReminder: UpdateReminderUseCase,
    private val getReminderByIdUseCase: GetReminderByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CreateRemindersUiState>(Loading)
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()

    fun addsRemindersInChallenge(
        challengeId: String,
        reminder: Reminder,
    ) {
        viewModelScope.launch {
            val reminderExisting = (uiState.value as CreateRemindersUiState.Idle).reminder

            val result = if (reminderExisting != null) {
                updateReminder(challengeId, reminder)
            } else {
                createReminder(challengeId, reminder)
            }


            result.onSuccess {
                _eventFlow.emit(NavigationEvent.NavigateToReminderList)
            }.onFailure {
                _uiState.value = Error(it.message ?: "Unknown error")
            }
        }
    }

    fun getReminderById(
        challengeId: String,
        reminderId: String? = null,
    ) {
        if (reminderId.isNullOrEmpty())
            _uiState.value = CreateRemindersUiState.Idle(null)

        viewModelScope.launch {
            getReminderByIdUseCase(
                challengeId = challengeId, reminderId = reminderId!!
            ).onSuccess { reminder ->
                _uiState.value = CreateRemindersUiState.Idle(reminder)
            }.onFailure {
                _uiState.value = CreateRemindersUiState.Idle(null)
            }
        }
    }
}

sealed class CreateRemindersUiState {
    object Loading : CreateRemindersUiState()
    data class Idle(val reminder: Reminder?) : CreateRemindersUiState()
    data class Error(val message: String) : CreateRemindersUiState()
}

sealed class NavigationEvent {
    data object NavigateToReminderList : NavigationEvent()
}