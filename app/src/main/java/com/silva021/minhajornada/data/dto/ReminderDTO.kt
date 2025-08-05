package com.silva021.minhajornada.data.dto

data class ReminderDTO(
    val id: String,
    val challengeId: String,
    val hour: Int,
    val minute: Int,
    val weekday: String,
    val frequency: String,
    val isActive: Boolean = false
)