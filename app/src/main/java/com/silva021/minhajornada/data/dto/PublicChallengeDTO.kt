package com.silva021.minhajornada.data.dto

import com.google.firebase.Timestamp
import java.util.UUID

data class PublicChallengeDTO(
    val id: String = UUID.randomUUID().toString(),
    val imageUrl: String = "",
    val title: String = "",
    val subtitle: String = "",
    val description: String = "",
    val creatorName: String = "",
    val participantsCount: Int = 0,
    val category: String = "",
    val duration: String ="",
    val createdAt: Timestamp = Timestamp.now(),
    val rules: List<String> = emptyList(),
    val benefits: List<String> = emptyList(),
    val trending: Boolean = false
)