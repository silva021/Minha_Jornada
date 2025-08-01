package com.silva021.minhajornada.data.repository

import com.silva021.minhajornada.data.api.ChallengesApi
import com.silva021.minhajornada.data.dto.ChallengesDTO

class ChallengesRepositoryImpl(
    val challengesApi: ChallengesApi,
) : ChallengeRepository {
    override suspend fun getChallenges(): ChallengesDTO {
        return challengesApi.getChallenges()
    }

}

interface ChallengeRepository {
    suspend fun getChallenges(): ChallengesDTO
//    suspend fun createChallenge(challenge: ChallengeDTO): ChallengeDTO
//    suspend fun updateChallengeProgress(challengeId: String, progress: Int): ChallengeDTO
//    suspend fun deleteChallenge(challengeId: String)

}