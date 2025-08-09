package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.Communities
import com.silva021.minhajornada.domain.model.toDomain

class GetCommunitiesByCategoryUseCase(
    private val repository: CommunitiesRepository
) {
    suspend operator fun invoke(category: CategoryType): Result<Communities> = runCatching {
        val communities = repository.getCommunities().map { it.toDomain() }

        Communities(
//            my = communities.my.filter { it.category == category },
//            featured = communities.featured.filter { it.category == category }
            my = listOf(),
            featured = communities
        )
    }
}