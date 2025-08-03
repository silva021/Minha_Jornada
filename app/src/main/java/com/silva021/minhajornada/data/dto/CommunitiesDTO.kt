package com.silva021.minhajornada.data.dto

data class CommunitiesDTO(
    val featured: List<CommunityDTO>,
    val my: List<CommunityDTO>,
)

data class CommunityDTO(
    val id: String,
    val imageUrl: String,
    val name: String,
    val category: String,
    val members: Int
)
