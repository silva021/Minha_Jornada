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
            val calAgora = Calendar.getInstance()
            val calData = Calendar.getInstance().apply { time = date }

            val year = calAgora.get(Calendar.YEAR) - calData.get(Calendar.YEAR)
            val month = calAgora.get(Calendar.MONTH) - calData.get(Calendar.MONTH) + year * 12

            "${month} m"
        }
    }
}
