package com.silva021.minhajornada.data.dto

data class PostDTO(
    val userImage: String,
    val userName: String,
    val timeAgo: String,
    val content: String,
    val likes: Int,
    val comments: Int,
)