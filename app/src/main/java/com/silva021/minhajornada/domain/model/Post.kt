package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.PostDTO

data class Post(
    val userImage: String,
    val userName: String,
    val timeAgo: String,
    val content: String,
    val likes: Int,
    val comments: Int,
)

fun PostDTO.toDomain(): Post {
    return Post(
        userImage = userImage,
        userName = userName,
        timeAgo = timeAgo,
        content = content,
        likes = likes,
        comments = comments
    )
}