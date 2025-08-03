package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.PublicChallengeDTO

data class PublicChallenge(
    val id: String,
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val creatorName: String,
    val participantsCount: Int,
    val category: CategoryType,
    val duration: DurationType,
    val isTrending: Boolean = false,
    val rules: List<String> = emptyList(),
    val benefits: List<String> = emptyList()
)

fun PublicChallengeDTO.toDomain(): PublicChallenge {
    return PublicChallenge(
        id = id,
        imageUrl = imageUrl,
        title = title,
        subtitle = subtitle,
        description = description,
        creatorName = creatorName,
        participantsCount = participantsCount,
        category = CategoryType.valueOf(category),
        duration = DurationType.valueOf(duration),
        isTrending = isTrending,
        rules = rules,
        benefits = benefits
    )
}