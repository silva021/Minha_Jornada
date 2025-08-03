package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.model.toDomain

class GetCommunityPostsUseCase(
    val repository: CommunitiesRepository,
) {
    suspend operator fun invoke(communityId: String): Result<List<Post>> = runCatching {
        repository.getPostsByCommunityId(communityId).map { it.toDomain() }
    }
}