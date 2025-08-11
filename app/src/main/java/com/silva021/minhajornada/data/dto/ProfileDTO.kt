package com.silva021.minhajornada.data.dto

import com.google.firebase.Timestamp
import com.silva021.minhajornada.domain.model.Profile

data class ProfileDTO(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val userName: String = "",
    val profilePictureUrl: String = "",
    val createdAt: Timestamp = Timestamp.now(),
)

fun Profile.toDTO(): ProfileDTO {
    return ProfileDTO(
        id = this.id,
        name = this.name,
        email = this.email,
        userName = this.userName,
        profilePictureUrl = this.profilePictureUrl,
        createdAt = this.createdAt
    )
}