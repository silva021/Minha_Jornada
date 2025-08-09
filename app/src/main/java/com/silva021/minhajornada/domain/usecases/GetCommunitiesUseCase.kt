package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.domain.model.toDomain

class GetCommunitiesUseCase(
    private val repository: CommunitiesRepository,
) {
    suspend operator fun invoke(): Result<List<Community>> = runCatching {
        repository.getCommunities().map { it.toDomain() }
    }
}