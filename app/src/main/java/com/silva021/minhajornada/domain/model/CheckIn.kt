package com.silva021.minhajornada.domain.model

import com.google.firebase.Timestamp
import com.silva021.minhajornada.data.dto.CheckInDTO

data class CheckIn(
    val id: String,
    val day: Int,
    val date: Timestamp,
    val note: String,
    val status: CheckInStatus,

)

enum class CheckInStatus(
    val label: String
) {
    SUCCESS("Sucesso"),
    FAILURE("Falha");
}

fun CheckInDTO.toDomain(): CheckIn {
    return CheckIn(
        id = this.id,
        day = this.day,
        date = this.date,
        note = this.note,
        status = CheckInStatus.valueOf(this.status)
    )
}