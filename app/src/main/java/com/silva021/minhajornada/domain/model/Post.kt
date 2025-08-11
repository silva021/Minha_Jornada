package com.silva021.minhajornada.domain.model

import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.silva021.minhajornada.data.dto.PostDTO
import com.silva021.minhajornada.data.dto.ProfileDTO

data class Post(
    val id: String,
    val owner: Profile,
    val createdAt: Timestamp,
    val content: String,
    val comments: Long,
    val isMine: Boolean,
)

fun PostDTO.toDomain(): Post {
    return Post(
        id = id,
        owner = ProfileDTO(
            id = authorId,
            name = authorName,
            profilePictureUrl = authorImage,
            userName = authorUserName
        ).toDomain(),
        createdAt = createdAt,
        content = text,
        comments = commentsCount,
        isMine = authorId == Firebase.auth.uid
    )
}