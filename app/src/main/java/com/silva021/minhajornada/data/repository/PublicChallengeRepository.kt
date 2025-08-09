package com.silva021.minhajornada.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.google.firebase.firestore.FieldValue
import com.silva021.minhajornada.data.dto.PublicChallengeDTO
import kotlinx.coroutines.tasks.await

class PublicChallengeRepositoryImpl() : PublicChallengeRepository {
    val publicChallengesCollection by lazy { FireStoreHelper.publicChallengesCollection }

    override suspend fun getPublicChallenges(): List<PublicChallengeDTO> {
        val challenges = publicChallengesCollection
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(PublicChallengeDTO::class.java) }

        return challenges
    }

    override suspend fun getPublicChallengeByCategory(category: String): List<PublicChallengeDTO> {
        val challenges = publicChallengesCollection
            .whereEqualTo("category", category)
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(PublicChallengeDTO::class.java) }

        return challenges
    }

    override suspend fun getPublicChallengeById(id: String): PublicChallengeDTO {
        val document = publicChallengesCollection.document(id).get().await()
        val publicChallenge = document.toObject(PublicChallengeDTO::class.java)
            ?: throw Exception("Desafio público não encontrado")
        return publicChallenge
    }

    override suspend fun createPublicChallenge(challenge: PublicChallengeDTO) {
        Firebase.auth.currentUser?.uid ?: throw Exception("Usuário não autenticado")
        publicChallengesCollection.document(challenge.id).set(challenge).await()
    }

    override suspend fun acceptPublicChallenge(id: String) {

    }

    override suspend fun incrementParticipantsCount(id: String) {
        publicChallengesCollection
            .document(id)
            .update("participantsCount", FieldValue.increment(1))
            .await()
    }
}

interface PublicChallengeRepository {
    suspend fun getPublicChallenges(): List<PublicChallengeDTO>
    suspend fun getPublicChallengeByCategory(category: String): List<PublicChallengeDTO>
    suspend fun getPublicChallengeById(id: String): PublicChallengeDTO
    suspend fun createPublicChallenge(challenge: PublicChallengeDTO)
    suspend fun acceptPublicChallenge(id: String)
    suspend fun incrementParticipantsCount(id: String)
}