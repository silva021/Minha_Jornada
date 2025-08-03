package com.silva021.minhajornada.data.repository

import com.silva021.minhajornada.data.api.ChallengesApi
import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.dto.PublicChallengeDTO
import com.silva021.minhajornada.ui.DatabaseFake

class ChallengesRepositoryImpl(
    val challengesApi: ChallengesApi,
) : ChallengeRepository {
    override suspend fun getChallenges(): List<ChallengeDTO> {
//        return challengesApi.getChallenges()
        return DatabaseFake.challengesDto
    }

    override suspend fun getPublicChallenges(): List<PublicChallengeDTO> {
        return DatabaseFake.publicChallenges
    }

}

interface ChallengeRepository {
    suspend fun getChallenges(): List<ChallengeDTO>
    suspend fun getPublicChallenges(): List<PublicChallengeDTO>
//    suspend fun createChallenge(challenge: ChallengeDTO): ChallengeDTO
//    suspend fun updateChallengeProgress(challengeId: String, progress: Int): ChallengeDTO
//    suspend fun deleteChallenge(challengeId: String)

}