package com.silva021.minhajornada.domain.model

data class Post(
    val userImage: String,
    val userName: String,
    val timeAgo: String,
    val content: String,
    val likes: Int,
    val comments: Int,
)