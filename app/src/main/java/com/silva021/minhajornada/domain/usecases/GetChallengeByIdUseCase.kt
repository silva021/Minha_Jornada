package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.toDomain

class GetChallengeByIdUseCase(
    private val repository: ChallengeRepository,
) {
    suspend operator fun invoke(
        id: String
    ): Result<Challenge> = runCatching {
        repository.getChallenges().find { it.id == id }!!.toDomain()
    }
}