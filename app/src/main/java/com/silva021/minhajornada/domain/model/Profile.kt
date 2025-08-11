package com.silva021.minhajornada.domain.model

import com.google.firebase.Timestamp
import com.silva021.minhajornada.data.dto.ProfileDTO

data class Profile(
    val id: String,
    val name: String,
    val email: String,
    val userName: String,
    val profilePictureUrl: String,
    val createdAt: Timestamp,
)

fun ProfileDTO.toDomain(): Profile {
    return Profile(
        id = this.id,
        name = this.name,
        userName = this.userName,
        profilePictureUrl = this.profilePictureUrl,
        createdAt = this.createdAt,
        email = this.email
    )
}
