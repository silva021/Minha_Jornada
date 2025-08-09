package com.silva021.minhajornada.domain.usecases

import com.google.firebase.Timestamp
import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.PublicChallengeRepository
import com.silva021.minhajornada.domain.model.PublicChallenge
import java.util.UUID

class AcceptPublicChallengeUseCase(
    private val repository: PublicChallengeRepository,
    private val challengeRepository: ChallengeRepository
) {
    suspend operator fun invoke(
        challenge: PublicChallenge
    ): Result<Unit> {
        return runCatching {
            challengeRepository.createChallenge(challenge.toChallengeDTO())
            repository.incrementParticipantsCount(challenge.id)
        }
    }
}

private fun PublicChallenge.toChallengeDTO() : ChallengeDTO {
    return ChallengeDTO(
        id = UUID.randomUUID().toString(),
        title = this.title,
        description = this.description,
        categoryType = this.categoryType.name,
        durationType = this.durationType.name,
        ownerName = this.creatorName,
        public = true,
        startDate = Timestamp.now(),
    )
}