package com.silva021.minhajornada.domain.model

data class PublicChallenge(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val creatorName: String,
    val participantsCount: Int,
    val category: CategoryType,
    val isTrending: Boolean = false
)