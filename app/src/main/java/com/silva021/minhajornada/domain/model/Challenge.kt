package com.silva021.minhajornada.domain.model

data class Challenge(
    val id: Int,
    val title: String,
    val description: String,
    val durationType: DurationType,
    val categoryType: CategoryType,
    val checkins: List<CheckIn>,
    val startDate: String,
)