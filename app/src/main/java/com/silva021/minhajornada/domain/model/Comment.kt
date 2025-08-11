package com.silva021.minhajornada.domain.model

import com.google.firebase.Timestamp
import com.silva021.minhajornada.data.dto.CommentDTO
import com.silva021.minhajornada.data.dto.ProfileDTO

data class Comment(
    val postId: String,
    val profile: Profile,
    val createdAt: Timestamp,
    val comment: String,
)

fun CommentDTO.toDomain(): Comment {
    return Comment(
        postId = id,
        profile = ProfileDTO(
            id = authorId,
            name = authorName,
            profilePictureUrl = authorImage,
            userName = authorUserName
        ).toDomain(),
        createdAt = createdAt,
        comment = text
    )
}