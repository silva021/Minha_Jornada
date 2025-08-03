package com.silva021.minhajornada.data.repository

import com.silva021.minhajornada.data.api.ProfileApi
import com.silva021.minhajornada.data.dto.ProfileDTO
import com.silva021.minhajornada.ui.profilesDTO

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi
) : ProfileRepository {
    override suspend fun getProfile(): ProfileDTO {
        return profilesDTO.first()
    }
}

interface ProfileRepository {
    suspend fun getProfile(): ProfileDTO
}