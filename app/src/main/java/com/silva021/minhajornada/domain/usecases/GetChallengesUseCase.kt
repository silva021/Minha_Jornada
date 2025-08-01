package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.domain.model.Challenges
import com.silva021.minhajornada.domain.model.toDomain

class GetChallengesUseCase(
    val repository: ChallengeRepository
) {
    suspend operator fun invoke() : Result<Challenges> {
        try {
            val challenges = repository.getChallenges().toDomain()
            return Result.success(challenges)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}