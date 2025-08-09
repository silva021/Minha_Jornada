package com.silva021.minhajornada.domain.model

import com.google.firebase.Timestamp
import com.silva021.minhajornada.data.dto.ChallengeDTO
import java.util.UUID

data class Challenge(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val durationType: DurationType,
    val categoryType: CategoryType,
    val ownerName: String,
    val checkins: List<CheckIn>,
    val reminders: List<Reminder>,
    val startDate: Timestamp
)


fun ChallengeDTO.toDomain(): Challenge {
    return Challenge(
        id = this.id,
        title = this.title,
        description = this.description,
        startDate = this.startDate,
        ownerName = this.ownerName,
        checkins = this.checkins.map { it.toDomain() },
        categoryType = CategoryType.valueOf(this.categoryType.uppercase()),
        durationType = DurationType.valueOf(this.durationType.uppercase()),
        reminders = this.reminders.map { it.toDomain() },
    )
}