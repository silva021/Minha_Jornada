package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.FeedRepository

class EditPostUseCase(
    private val repository: FeedRepository,
) {
    suspend operator fun invoke(
        communityId: String,
        postId: String,
        text: String,
    ) = runCatching {
        repository.editPost(
            communityId = communityId,
            postId = postId,
            text = text
        )
    }
}