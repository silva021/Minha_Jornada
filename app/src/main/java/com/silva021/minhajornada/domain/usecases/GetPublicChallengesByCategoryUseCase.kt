package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.PublicChallengeRepository
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.PublicChallenges
import com.silva021.minhajornada.domain.model.toDomain

class GetPublicChallengesByCategoryUseCase(
    val repository: PublicChallengeRepository,
) {
    suspend operator fun invoke(categoryType: CategoryType): Result<PublicChallenges> = runCatching {
        val challenges = repository
            .getPublicChallengeByCategory(categoryType.name)
            .map { it.toDomain() }

        val (trending, popular) = challenges.partition { it.isTrending }

        PublicChallenges(
            popularChallenges = popular,
            trendingChallenges = trending
        )
    }
}