package com.silva021.minhajornada.domain.model

import com.google.firebase.Timestamp
import java.util.UUID

data class Challenge(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val durationType: DurationType,
    val categoryType: CategoryType,
    val checkins: List<CheckIn>,
    val reminders: List<Reminder>,
    val startDate: Timestamp
)