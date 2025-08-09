package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.PublicChallenges
import com.silva021.minhajornada.domain.model.toDomain

class GetPublicChallengesByCategoryUseCase(
    val repository: ChallengeRepository,
) {
    suspend operator fun invoke(categoryType: CategoryType): Result<PublicChallenges> = runCatching {
        val filteredChallenges = repository
            .getPublicChallengeByCategory(categoryType.name)
            .map { it.toDomain() }

        PublicChallenges(
            popularChallenges = filteredChallenges.filter { it.isTrending.not() },
            trendingChallenges = filteredChallenges.filter { it.isTrending }
        )
    }
}