package com.silva021.minhajornada.data.api

import com.silva021.minhajornada.data.dto.ChallengesDTO
import retrofit2.http.GET

interface ChallengesApi {
    @GET("challenges")
    suspend fun getChallenges(): ChallengesDTO
}