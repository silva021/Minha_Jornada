package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.dto.toDTO
import com.silva021.minhajornada.data.repository.ReminderRepository
import com.silva021.minhajornada.domain.model.Reminder

class UpdateReminderUseCase(
    val repository: ReminderRepository
) {
    suspend operator fun invoke(
        challengeId: String,
        reminder: Reminder
    ) = runCatching {
        repository.updateReminder(
            challengeId = challengeId,
            updated = reminder.toDTO()
        )
    }
}