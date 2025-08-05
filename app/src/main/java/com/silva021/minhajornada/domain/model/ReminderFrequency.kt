package com.silva021.minhajornada.domain.model

enum class ReminderFrequency(val label: String) {
    DAILY("Diário"),
    WEEKLY("Semanal"),
//    MONTHLY("Mensal"),
    WEEKDAYS("Dias úteis"),
    WEEKENDS("Finais de semana"),
}