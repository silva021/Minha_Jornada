package com.silva021.minhajornada.data.dto

data class PublicChallengeDTO(
    val id: String,
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val creatorName: String,
    val participantsCount: Int,
    val category: String,
    val duration: String,
    val rules: List<String> = emptyList(),
    val benefits: List<String> = emptyList(),
    val isTrending: Boolean = false
)