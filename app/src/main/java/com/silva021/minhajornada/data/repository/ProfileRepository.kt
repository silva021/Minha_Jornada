package com.silva021.minhajornada.data.repository

import com.silva021.minhajornada.data.api.ProfileApi
import com.silva021.minhajornada.data.dto.ProfileDTO

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi
) : ProfileRepository {
    override suspend fun getProfile(): ProfileDTO {
        return profileApi.getProfile()
    }
}

interface ProfileRepository {
    suspend fun getProfile(): ProfileDTO
}