package com.silva021.minhajornada.data.dto

import com.google.firebase.Timestamp
import com.silva021.minhajornada.domain.model.Challenge

data class ChallengeDTO(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val categoryType: String = "",
    val durationType: String = "",
    val ownerName: String = "",
    val checkins: List<CheckInDTO> = emptyList(),
    val reminders: List<ReminderDTO> = emptyList(),
    val startDate: Timestamp = Timestamp.now(),
)

fun Challenge.toDTO(): ChallengeDTO {
    return ChallengeDTO(
        id = this.id,
        title = this.title,
        description = this.description,
        categoryType = this.categoryType.name,
        durationType = this.durationType.name,
        ownerName = this.ownerName,
        checkins = this.checkins.map { it.toDTO() },
        reminders = this.reminders.map { it.toDTO() },
        startDate = this.startDate,
    )
}