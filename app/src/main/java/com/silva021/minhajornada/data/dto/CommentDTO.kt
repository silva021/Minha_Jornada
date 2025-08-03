package com.silva021.minhajornada.data.dto

data class CommentDTO(
    val postId: String,
    val profile: ProfileDTO,
    val createdAt: String,
    val comment: String
)