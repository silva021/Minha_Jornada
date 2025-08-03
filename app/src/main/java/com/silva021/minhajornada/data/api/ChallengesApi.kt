package com.silva021.minhajornada.data.api

import com.silva021.minhajornada.data.dto.ChallengeDTO
import retrofit2.http.GET

interface ChallengesApi {
    @GET("challenges")
    suspend fun getChallenges(): List<ChallengeDTO>
}