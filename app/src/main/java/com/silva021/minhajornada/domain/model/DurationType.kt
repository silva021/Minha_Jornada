package com.silva021.minhajornada.domain.model

enum class DurationType(val days: Int, val label: String) {
    THREE_DAYS(3, "3 dias"),
    SEVEN_DAYS(7, "7 dias"),
    FOURTEEN_DAYS(14, "14 dias"),
    TWENTY_ONE_DAYS(21, "21 dias"),
    THIRTY_DAYS(30, "30 dias");

    companion object {
        fun fromDays(days: Int): DurationType? =
            entries.find { it.days == days }
    }
}