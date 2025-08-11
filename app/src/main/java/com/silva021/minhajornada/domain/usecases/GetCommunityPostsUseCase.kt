package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.FeedRepository
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.model.toDomain

class GetCommunityPostsUseCase(
    val repository: FeedRepository,
) {
    suspend operator fun invoke(communityId: String): Result<List<Post>> = runCatching {
        repository.getFeed(
            communityId = communityId,
        ).map { it.toDomain() }
    }
}