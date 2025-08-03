package com.silva021.minhajornada.domain.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun String.formatRelativeDate(): String {
    val now = Date()
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)

    val diffMillis = now.time - date.time

    val diffDays = TimeUnit.MILLISECONDS.toDays(diffMillis)

    return when {
        diffDays < 1 -> "hoje"
        diffDays in 1..7 -> "${diffDays}d"
        diffDays in 8..27 -> "${diffDays / 7} sem"
        else -> {
            val calNow = Calendar.getInstance()
            val calDate = Calendar.getInstance().apply { time = date }

            val year = calNow.get(Calendar.YEAR) - calDate.get(Calendar.YEAR)
            val month = calNow.get(Calendar.MONTH) - calDate.get(Calendar.MONTH) + year * 12

            "${month} m"
        }
    }
}

fun String.getYears(): String {
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    val calendarInstance = Calendar.getInstance().apply { time = date }
    return calendarInstance.get(Calendar.YEAR).toString()
}


