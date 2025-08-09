package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.PublicChallengeRepository
import com.silva021.minhajornada.ui.DatabaseFake

class CreatePublicChallengeUseCase(
    private val repository: PublicChallengeRepository
) {
    suspend operator fun invoke() = runCatching {
        DatabaseFake.publicChallenges.forEach { challenge ->
            repository.createPublicChallenge(challenge)
        }
    }
}