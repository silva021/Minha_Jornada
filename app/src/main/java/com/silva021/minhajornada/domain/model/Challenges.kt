package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.dto.ChallengesDTO

data class Challenges(
    val actives: List<Challenge>,
    val completed: List<Challenge>
)

data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val progress: Int,
    val startDate: String,
    val endDate: String? = null
)

fun ChallengesDTO.toDomain(): Challenges {
    return Challenges(
        actives = this.actives.map { it.toDomain() },
        completed = this.completed.map { it.toDomain() }
    )
}

fun ChallengeDTO.toDomain(): Challenge {
    return Challenge(
        id = this.id,
        title = this.title,
        description = this.description,
        progress = this.progress,
        startDate = this.startDate,
        endDate = this.endDate
    )
}