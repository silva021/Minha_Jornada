package com.silva021.minhajornada.domain.extension

import com.silva021.minhajornada.domain.model.Challenge
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Challenge.isExpired(): Boolean {
    val end = this.calculateChallengeEndDate()
    val today = Calendar.getInstance()

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val endDate = dateFormat.parse(end) ?: return false

    return today.time.after(endDate)
}

fun Challenge.allCheckInDone() = this.checkins.size == this.durationType.days

fun Challenge.calculateChallengeProgress(): Int {
    val completedDays = this.checkins.distinctBy { it.day }.size
    val progress = (completedDays.toDouble() / this.durationType.days) * 100
    return progress.toInt().coerceIn(0, 100)
}

fun Challenge.calculateChallengeDaysLeft(): Int {
    val startDate = this.startDate.toDate()

    val startCal = Calendar.getInstance().apply {
        time = startDate
        add(Calendar.DAY_OF_YEAR, durationType.days)
    }

    val today = Calendar.getInstance()

    val diffMillis = startCal.timeInMillis - today.timeInMillis
    val daysLeft = TimeUnit.MILLISECONDS.toDays(diffMillis).toInt()

    return if (daysLeft < 0) 0 else daysLeft
}

fun Challenge.calculateChallengeEndDate(): String {
    val startDate = this.startDate.toDate()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val calendar = Calendar.getInstance().apply {
        time = startDate
        add(Calendar.DAY_OF_YEAR, durationType.days)
    }

    return dateFormat.format(calendar.time)
}

fun Challenge.currentChallengeDay(): Int {
    val start = Calendar.getInstance().apply {
        time = this@currentChallengeDay.startDate.toDate()
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    if (today.before(start)) return 0

    val diffDays = ((today.timeInMillis - start.timeInMillis) / (24L * 60 * 60 * 1000)).toInt()
    val dayNumber = diffDays + 1

    return dayNumber.coerceIn(1, durationType.days)
}
