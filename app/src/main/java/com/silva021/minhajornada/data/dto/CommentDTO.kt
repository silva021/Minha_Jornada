package com.silva021.minhajornada.data.dto

import com.google.firebase.Timestamp

data class CommentDTO(
    val id: String = "",
    val authorId: String = "",
    val authorName: String = "",
    val authorImage: String = "",
    val authorUserName: String = "",
    val text: String = "",
    val createdAt: Timestamp = Timestamp.now(),
)