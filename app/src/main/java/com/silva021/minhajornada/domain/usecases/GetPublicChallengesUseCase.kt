package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.domain.model.PublicChallenges
import com.silva021.minhajornada.domain.model.toDomain

class GetPublicChallengesUseCase(
    val repository: ChallengeRepository,
) {
    suspend operator fun invoke(): Result<PublicChallenges> = runCatching {
        val challenges = repository.getPublicChallenges().map { it.toDomain() }

        val (trending, popular) = challenges.partition { it.isTrending }

        PublicChallenges(
            popularChallenges = popular,
            trendingChallenges = trending
        )
    }
}