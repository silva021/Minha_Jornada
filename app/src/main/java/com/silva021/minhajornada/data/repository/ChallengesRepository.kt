package com.silva021.minhajornada.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.dto.PublicChallengeDTO
import kotlinx.coroutines.tasks.await

class ChallengesRepositoryImpl() : ChallengeRepository {
    val userChallengesCollection by lazy { FireStoreHelper.userChallengesCollection }
    val publicChallengesCollection by lazy { FireStoreHelper.publicChallengesCollection }

    override suspend fun getChallenges(): List<ChallengeDTO> {
        val challenges = userChallengesCollection
            .get()
            .await()
            .documents
            .mapNotNull { document ->
                document.toObject(ChallengeDTO::class.java)
            }

        return challenges
    }

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

    override suspend fun createChallenge(challenge: ChallengeDTO) {
        Firebase.auth.currentUser?.uid ?: throw Exception("Usuário não autenticado")
        userChallengesCollection.document(challenge.id).set(challenge).await()
    }

    override suspend fun createPublicChallenge(challenge: PublicChallengeDTO) {
        Firebase.auth.currentUser?.uid ?: throw Exception("Usuário não autenticado")
        publicChallengesCollection.document(challenge.id).set(challenge).await()
    }

    override suspend fun updateChallengeProgress(
        challengeId: String,
        progress: Int,
    ): ChallengeDTO {
        TODO("Not yet implemented")
    }

    override suspend fun deleteChallenge(challengeId: String) {
        userChallengesCollection
            .document(challengeId)
            .delete()
            .await()
    }

    override suspend fun completeChallenge(
        challengeId: String
    ) {
        userChallengesCollection
            .document(challengeId)
            .update(
                mapOf(
                    "completed" to true,
                )
            )
    }

}

interface ChallengeRepository {
    suspend fun getChallenges(): List<ChallengeDTO>
    suspend fun getPublicChallenges(): List<PublicChallengeDTO>
    suspend fun getPublicChallengeByCategory(category: String): List<PublicChallengeDTO>
    suspend fun createChallenge(challenge: ChallengeDTO)
    suspend fun createPublicChallenge(challenge: PublicChallengeDTO)
    suspend fun updateChallengeProgress(challengeId: String, progress: Int): ChallengeDTO
    suspend fun deleteChallenge(challengeId: String)
    suspend fun completeChallenge(challengeId: String)
}