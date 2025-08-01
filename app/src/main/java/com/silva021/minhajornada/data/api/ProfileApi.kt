package com.silva021.minhajornada.data.api

import com.silva021.minhajornada.data.dto.ProfileDTO
import retrofit2.http.GET

interface ProfileApi {
    @GET("profile")
    suspend fun getProfile(): ProfileDTO
}