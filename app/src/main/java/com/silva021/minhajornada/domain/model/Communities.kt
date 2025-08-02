package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.CategoryDTO
import com.silva021.minhajornada.data.dto.CommunitiesDTO
import com.silva021.minhajornada.data.dto.CommunityDTO

data class Communities(
    val featured: List<Community>,
    val my: List<Community>,
)

data class Community(
    val id: String,
    val imageUrl: String,
    val name: String,
    val members: String,
)

data class Category(
    val id: String,
    val type: CategoryType,
    val name: String,
)


fun CommunitiesDTO.toDomain(): Communities {
    return Communities(
        my = my.map { it.toDomain() },
        featured = featured.map { it.toDomain() }
    )
}

fun CommunityDTO.toDomain() = Community(
    id = id,
    imageUrl = imageUrl,
    name = name,
    members = "$members membros"
)

fun CategoryDTO.toDomain() = Category(
    id = id,
    type = CategoryType.valueOf(type.uppercase()),
    name = name
)