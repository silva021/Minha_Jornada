package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.PostDTO

data class Post(
    val id: String,
    val owner: Profile,
    val createdAt: String,
    val content: String,
    val likes: Int,
    val comments: Int,
)

fun PostDTO.toDomain(): Post {
    return Post(
        id = id,
        owner = profile.toDomain(),
        createdAt = createdAt,
        content = content,
        likes = likes,
        comments = comments
    )
}