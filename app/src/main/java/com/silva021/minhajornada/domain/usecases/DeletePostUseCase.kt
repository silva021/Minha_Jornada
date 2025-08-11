package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.FeedRepository

class DeletePostUseCase(
    private val repository: FeedRepository,
) {
    suspend operator fun invoke(
        communityId: String,
        postId: String
    ) = runCatching {
        repository.deletePost(
            communityId = communityId,
            postId = postId
        )
    }
}