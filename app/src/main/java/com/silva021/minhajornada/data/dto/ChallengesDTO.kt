package com.silva021.minhajornada.data.dto

data class ChallengeDTO(
    val id: Int,
    val title: String,
    val description: String,
    val categoryType: String,
    val durationType: String,
    val checkins: List<CheckInDTO>? = null,
    val startDate: String,
)
