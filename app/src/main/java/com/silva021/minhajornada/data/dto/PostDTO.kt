package com.silva021.minhajornada.data.dto

data class PostDTO(
    val id: String,
    val profile: ProfileDTO,
    val createdAt: String,
    val content: String,
    val likes: Int,
    val comments: Int,
)