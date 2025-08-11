package com.silva021.minhajornada.domain.extension

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Timestamp.formatRelativeDate(): String {
    val now = Date()
    val date = this.toDate()

    val diffMillis = now.time - date.time

    val diffDays = TimeUnit.MILLISECONDS.toDays(diffMillis)

    return when {
        diffDays < 1 -> "Hoje"
        diffDays in 1..7 -> "${diffDays}d atrás"
        diffDays in 8..27 -> "${diffDays / 7} sem atrás"
        else -> {
            val calNow = Calendar.getInstance()
            val calDate = Calendar.getInstance().apply { time = date }

            val year = calNow.get(Calendar.YEAR) - calDate.get(Calendar.YEAR)
            val month = calNow.get(Calendar.MONTH) - calDate.get(Calendar.MONTH) + year * 12

            "${month} m"
        }
    }
}

fun Timestamp.getYears(): String {
    val calendarInstance = Calendar.getInstance().apply { time = this@getYears.toDate() }
    return calendarInstance.get(Calendar.YEAR).toString()
}


