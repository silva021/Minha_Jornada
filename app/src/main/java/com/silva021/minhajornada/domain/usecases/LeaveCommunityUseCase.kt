package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.CommunitiesRepository

class LeaveCommunityUseCase(
    val repository: CommunitiesRepository,
) {
    suspend operator fun invoke(id: String): Result<Unit> = runCatching {
        repository.leaveCommunity(id)
    }
}