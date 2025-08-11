package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.dto.CommentDTO
import com.silva021.minhajornada.data.repository.FeedRepository
import com.silva021.minhajornada.domain.model.Profile
import java.util.UUID

class CreateCommentUseCase(
    private val repository: FeedRepository,
) {
    suspend operator fun invoke(
        communityId: String,
        postId: String,
        text: String,
        owner: Profile
    ) = runCatching {
        repository.createComment(
            communityId = communityId,
            postId = postId,
            commentDTO = CommentDTO(
                id = UUID.randomUUID().toString(),
                authorId = owner.id,
                authorName = owner.name,
                authorImage = owner.profilePictureUrl,
                authorUserName = owner.userName,
                text = text,
            )
        )
    }
}