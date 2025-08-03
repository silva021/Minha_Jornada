package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.domain.model.toDomain

class GetCommunityByIdUseCase(
    private val repository: CommunitiesRepository,
) {
    suspend operator fun invoke(id: String): Result<Community> = runCatching {
        val communities = repository.getCommunities().toDomain()

        communities.my.find { it.id == id }?.let {
            return Result.success(it)
        }

        communities.featured.find { it.id == id }?.let {
            return Result.success(it)
        } ?: run {
            throw Exception("Community with id $id not found")
        }
    }
}