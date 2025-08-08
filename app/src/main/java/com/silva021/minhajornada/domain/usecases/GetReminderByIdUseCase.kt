package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ReminderRepository
import com.silva021.minhajornada.domain.model.Reminder
import com.silva021.minhajornada.domain.model.toDomain

class GetReminderByIdUseCase(
    private val repository: ReminderRepository,
) {
    suspend operator fun invoke(
        challengeId: String,
        reminderId: String
    ): Result<Reminder> = runCatching {
        repository.getReminderById(challengeId, reminderId)
            .toDomain()
    }
}