package com.silva021.minhajornada.domain.model

data class Report(
    val status: ReportStatus,
    val day: String,
    val date: String,
    val notes: String,
)

enum class ReportStatus {
    SUCCESS,
    FAILURE,
}