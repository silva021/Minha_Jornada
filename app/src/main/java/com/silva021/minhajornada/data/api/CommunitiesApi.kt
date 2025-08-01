package com.silva021.minhajornada.data.api

import com.silva021.minhajornada.data.dto.CommunitiesDTO
import retrofit2.http.GET

interface CommunitiesApi {
    @GET("communities")
    suspend fun getCommunities(): CommunitiesDTO
}