package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.ReminderDTO
import kotlin.Int

data class Reminder(
    val id: String,
    val challengeId: String,
    val hour: Int,
    val minute: Int,
    val weekday: Weekday,
    val frequency: ReminderFrequency,
    val isActive: Boolean = false
)

fun ReminderDTO.toDomain(): Reminder {
    return Reminder(
        id = id,
        challengeId = challengeId,
        hour = hour,
        minute = minute,
        weekday = Weekday.valueOf(weekday),
        frequency = ReminderFrequency.valueOf(frequency),
        isActive = isActive
    )
}