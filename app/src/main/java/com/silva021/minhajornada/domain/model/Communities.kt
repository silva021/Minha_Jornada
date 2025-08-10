package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.CommunitiesDTO
import com.silva021.minhajornada.data.dto.CommunityDTO

data class Communities(
    val discover: List<Community>,
    val my: List<Community>,
)

data class Community(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val category: CategoryType,
    val about: String,
    val membersCount: Int,
)

fun CommunitiesDTO.toDomain(): Communities {
    return Communities(
        my = my.map { it.toDomain() },
        discover = discover.map { it.toDomain() }
    )
}

fun CommunityDTO.toDomain() = Community(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    category = CategoryType.valueOf(category),
    about = about,
    membersCount = membersCount,
)
