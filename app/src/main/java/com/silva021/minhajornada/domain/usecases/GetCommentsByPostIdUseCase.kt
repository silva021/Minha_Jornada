package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.FeedRepository
import com.silva021.minhajornada.domain.model.Comment
import com.silva021.minhajornada.domain.model.toDomain

class GetCommentsByPostIdUseCase(
    val repository: FeedRepository,
) {
    suspend operator fun invoke(
        communityId: String,
        postId: String,
    ): Result<List<Comment>> = runCatching {
        repository.getComments(
            communityId = communityId,
            postId = postId
        ).map { it.toDomain() }
    }
}
