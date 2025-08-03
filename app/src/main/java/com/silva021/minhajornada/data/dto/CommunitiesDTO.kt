package com.silva021.minhajornada.data.dto

data class CommunitiesDTO(
    val featured: List<CommunityDTO>,
    val my: List<CommunityDTO>,
)

data class CommunityDTO(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val category: String,
    val about: String,
    val members: Int
)
