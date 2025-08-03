package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.ChallengeDTO

data class Challenges(
    val actives: List<Challenge>,
    val completed: List<Challenge>
)

data class Challenge(
    val id: Int,
    val title: String,
    val description: String,
    val durationType: DurationType,
    val categoryType: CategoryType,
    val checkins: List<CheckIn>,
    val startDate: String,
)

fun ChallengeDTO.toDomain(): Challenge {
    return Challenge(
        id = this.id,
        title = this.title,
        description = this.description,
        startDate = this.startDate,
        checkins = this.checkins?.map { it.toDomain() } ?: emptyList(),
        categoryType = CategoryType.valueOf(this.categoryType.uppercase()),
        durationType = DurationType.valueOf(this.durationType.uppercase())
    )
}

enum class ChallengeResult {
    SUCCESS,
    FAILED
}