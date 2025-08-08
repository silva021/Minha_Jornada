package com.silva021.minhajornada.data.dto

import com.silva021.minhajornada.domain.model.CheckIn

data class CheckInDTO(
    val day: Int = 0,
    val date: String = "",
    val note: String = "",
    val status: String = "SUCCESS"
)

fun CheckIn.toDTO(): CheckInDTO {
    return CheckInDTO(
        day = this.day,
        date = this.date,
        note = this.note,
        status = this.status.name
    )
}