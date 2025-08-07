package com.silva021.minhajornada.data.dto

import com.silva021.minhajornada.domain.model.Profile

data class ProfileDTO(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val username: String = "",
    val profilePictureUrl: String = "",
    val createdAt: String = "",
)

fun Profile.toDTO(): ProfileDTO {
    return ProfileDTO(
        id = this.id,
        name = this.name,
        email = this.email,
        username = this.username,
        profilePictureUrl = this.profilePictureUrl,
        createdAt = this.createdAt
    )
}