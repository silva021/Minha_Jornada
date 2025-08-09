package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.PublicChallengeRepository
import com.silva021.minhajornada.domain.model.PublicChallenge
import com.silva021.minhajornada.domain.model.toDomain

class GetPublicChallengeByIdUseCase(
    private val repository: PublicChallengeRepository,
) {
    suspend operator fun invoke(
        id: String
    ): Result<PublicChallenge> = runCatching {
        repository.getPublicChallengeById(id).toDomain()
    }
}