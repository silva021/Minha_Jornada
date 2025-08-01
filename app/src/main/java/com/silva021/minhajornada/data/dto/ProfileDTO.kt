package com.silva021.minhajornada.data.dto

data class ProfileDTO(
    val id: String,
    val name: String,
    val username: String,
    val profilePictureUrl: String,
    val memberSince: String,
    val stats: ProfileStatsDTO
)

data class ProfileStatsDTO(
    val challenges: Int,
    val following: Int,
    val followers: Int
)