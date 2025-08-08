package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.dto.CheckInDTO
import com.silva021.minhajornada.data.dto.ReminderDTO

data class Challenges(
    val actives: List<Challenge>,
    val completed: List<Challenge>
)

fun ChallengeDTO.toDomain(): Challenge {
    return Challenge(
        id = this.id,
        title = this.title,
        description = this.description,
        startDate = this.startDate,
        checkins = this.checkins.map { it.toDomain() },
        categoryType = CategoryType.valueOf(this.categoryType.uppercase()),
        durationType = DurationType.valueOf(this.durationType.uppercase()),
        reminders = this.reminders.map { it.toDomain() },
    )
}
