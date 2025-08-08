package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.dto.toDTO
import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.ReminderRepository
import com.silva021.minhajornada.domain.model.Challenge

class CreateChallengeUseCase(
    val repository: ChallengeRepository
) {
    suspend operator fun invoke(
        challenge: Challenge
    ) = runCatching {
        repository.createChallenge(challenge.toDTO())
    }
}