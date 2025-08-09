package com.silva021.minhajornada.data.dto

import com.google.firebase.Timestamp
import com.silva021.minhajornada.domain.model.CheckIn

data class CheckInDTO(
    val id: String = "",
    val day: Int = 0,
    val date: Timestamp = Timestamp.now(),
    val note: String = "",
    val status: String = "SUCCESS"
)

fun CheckIn.toDTO(): CheckInDTO {
    return CheckInDTO(
        id = this.id,
        day = this.day,
        date = this.date,
        note = this.note,
        status = this.status.name
    )
}