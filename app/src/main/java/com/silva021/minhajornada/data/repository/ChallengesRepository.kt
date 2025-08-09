package com.silva021.minhajornada.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.dto.PublicChallengeDTO
import kotlinx.coroutines.tasks.await

class ChallengesRepositoryImpl() : ChallengeRepository {
    val userChallengesCollection by lazy { FireStoreHelper.userChallengesCollection }

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

    override suspend fun createChallenge(challenge: ChallengeDTO) {
        Firebase.auth.currentUser?.uid ?: throw Exception("Usuário não autenticado")
        userChallengesCollection.document(challenge.id).set(challenge).await()
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
    suspend fun createChallenge(challenge: ChallengeDTO)
    suspend fun deleteChallenge(challengeId: String)
    suspend fun completeChallenge(challengeId: String)
}