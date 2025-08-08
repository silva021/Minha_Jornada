package com.silva021.minhajornada.data.dto

import com.silva021.minhajornada.domain.model.Reminder

data class ReminderDTO(
    val id: String = "0",
    val hour: String = "0",
    val minute: String = "0",
    val weekday: String = "",
    val frequency: String = "",
    val active: Boolean = false
)

fun Reminder.toDTO(): ReminderDTO {
    return ReminderDTO(
        id = this.id,
        hour = this.hour.toString(),
        minute = this.minute.toString(),
        weekday = this.weekday.name,
        frequency = this.frequency.name,
        active = this.active
    )
}