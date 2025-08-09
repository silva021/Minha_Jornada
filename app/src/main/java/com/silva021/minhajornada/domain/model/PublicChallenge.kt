package com.silva021.minhajornada.domain.model

import com.google.firebase.Timestamp
import com.silva021.minhajornada.data.dto.PublicChallengeDTO

data class PublicChallenge(
    val id: String,
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val creatorName: String,
    val participantsCount: Int,
    val categoryType: CategoryType,
    val durationType: DurationType,
    val createdAt: Timestamp,
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
        categoryType = CategoryType.valueOf(category),
        durationType = DurationType.valueOf(duration),
        createdAt = createdAt,
        isTrending = trending,
        rules = rules,
        benefits = benefits
    )
}