package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.dto.toDTO
import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.CheckInRepository
import com.silva021.minhajornada.data.repository.ReminderRepository
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.CheckIn
import com.silva021.minhajornada.domain.model.Reminder

class CreateCheckInUseCase(private val repository: CheckInRepository) {
    suspend operator fun invoke(
        challengeId: String,
        checkIn: CheckIn
    ) = runCatching {
        repository.upsertCheckIn(
            challengeId = challengeId,
            checkIn = checkIn.toDTO()
        )
    }
}
