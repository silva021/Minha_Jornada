package com.silva021.minhajornada.domain.model

import com.silva021.minhajornada.data.dto.ProfileDTO

data class Profile(
    val id: String,
    val name: String,
    val email: String,
    val username: String,
    val profilePictureUrl: String,
    val createdAt: String,
)

fun ProfileDTO.toDomain(): Profile {
    return Profile(
        id = this.id,
        name = this.name,
        username = this.username,
        profilePictureUrl = this.profilePictureUrl,
        createdAt = this.createdAt,
        email = this.email
    )
}
