package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.ProfileStatsDTO

data class ProfileStats(
    val challenges: Int,
    val following: Int,
    val followers: Int
)

fun ProfileStatsDTO.toDomain(): ProfileStats {
    return ProfileStats(
        challenges = this.challenges,
        following = this.following,
        followers = this.followers
    )
}
