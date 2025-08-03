package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.domain.model.PublicChallenges
import com.silva021.minhajornada.domain.model.toDomain

class GetPublicChallengesUseCase(
    val repository: ChallengeRepository,
) {
    suspend operator fun invoke(): Result<PublicChallenges> = runCatching {
        val publicChallenges = repository.getPublicChallenges()

        PublicChallenges(
            popularChallenges = publicChallenges.filter { it.isTrending.not() }.map { it.toDomain() },
            trendingChallenges = publicChallenges.filter { it.isTrending }.map { it.toDomain() }
        )
    }
}