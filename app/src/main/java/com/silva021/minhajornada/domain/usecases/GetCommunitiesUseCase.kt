package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.Communities
import com.silva021.minhajornada.domain.model.toDomain

class GetCommunitiesUseCase(
    private val repository: CommunitiesRepository,
) {
    suspend operator fun invoke(
        category: CategoryType = CategoryType.ALL,
    ): Result<Communities> = runCatching {

        val joinedIds = repository.getMemberCommunityIds().toSet()
        val myCommunities = repository.getCommunitiesByIds(joinedIds, category).map { it.toDomain() }
        val discover = repository.getCommunities(category).filter { it.id !in joinedIds }.map { it.toDomain() }

        Communities(my = myCommunities, discover = discover)
    }
}