package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.domain.extension.isExpired
import com.silva021.minhajornada.domain.model.Challenges
import com.silva021.minhajornada.domain.model.toDomain

class GetChallengesUseCase(
    val repository: ChallengeRepository,
) {
    suspend operator fun invoke(): Result<Challenges> = runCatching {
        val challenges = repository.getChallenges().map { it.toDomain() }

        val (actives, completed) = challenges.partition { it.isExpired().not() && it.isCompleted.not() }

        Challenges(
            actives = actives,
            completed = completed
        )
    }
}