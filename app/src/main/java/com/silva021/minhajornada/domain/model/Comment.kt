package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.CommentDTO

data class Comment(
    val postId: String,
    val profile: Profile,
    val createdAt: String,
    val comment: String
)

fun CommentDTO.toDomain(): Comment {
    return Comment(
        postId = postId,
        profile = profile.toDomain(),
        createdAt = createdAt,
        comment = comment
    )
}