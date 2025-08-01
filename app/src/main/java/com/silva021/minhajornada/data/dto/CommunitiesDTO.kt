package com.silva021.minhajornada.data.dto

data class CommunitiesDTO(
    val featured: List<CommunityDTO>,
    val categories: List<CategoryDTO>
)

data class CommunityDTO(
    val id: String,
    val imageUrl: String,
    val name: String,
    val members: Int
)

data class CategoryDTO(
    val id: String,
    val type: String,
    val name: String
)