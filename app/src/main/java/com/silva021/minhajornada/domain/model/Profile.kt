package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.ProfileDTO
import com.silva021.minhajornada.data.dto.ProfileStatsDTO

data class Profile(
    val id: String,
    val name: String,
    val username: String,
    val profilePictureUrl: String,
    val createdAt: String,
    val stats: ProfileStats
)

data class ProfileStats(
    val challenges: Int,
    val following: Int,
    val followers: Int
)

fun ProfileDTO.toDomain(): Profile {
    return Profile(
        id = this.id,
        name = this.name,
        username = this.username,
        profilePictureUrl = this.profilePictureUrl,
        createdAt = this.createdAt,
        stats = this.stats.toDomain()
    )
}

fun ProfileStatsDTO.toDomain(): ProfileStats {
    return ProfileStats(
        challenges = this.challenges,
        following = this.following,
        followers = this.followers
    )
}
