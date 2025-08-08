package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ReminderRepository

class DeleteReminderUseCase(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(
        challengeId: String,
        reminderId: String
    ) = runCatching {
        repository.deleteReminder(
            challengeId = challengeId,
            reminderId = reminderId
        )
    }
}