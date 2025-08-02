package com.silva021.minhajornada.data.dto

data class ChallengesDTO(
    val actives: List<ChallengeDTO>,
    val completed: List<ChallengeDTO>
)

data class ChallengeDTO(
    val id: Int,
    val title: String,
    val description: String,
    val progress: Int,
    val startDate: String,
    val endDate: String?
)