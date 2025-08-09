package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository

class DeleteChallengeUseCase(
    private val repository: ChallengeRepository
) {
    suspend operator fun invoke(
        challengeId: String
    ) = runCatching {
        repository.deleteChallenge(challengeId = challengeId)
    }
}