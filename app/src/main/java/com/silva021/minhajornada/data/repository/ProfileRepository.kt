package com.silva021.minhajornada.data.repository

import com.silva021.minhajornada.data.api.ProfileApi
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.ProfileDTO
import com.silva021.minhajornada.ui.profilesDTO
import kotlinx.coroutines.tasks.await
import kotlin.text.set

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi
) : ProfileRepository {
    val usersCollection = FireStoreHelper.usersCollection

    override suspend fun getProfile(): ProfileDTO {
        return profilesDTO.first()
    }

    override suspend fun createProfile(profile: ProfileDTO) {
        usersCollection.document(profile.id).set(profile).await()
    }
}

interface ProfileRepository {
    suspend fun getProfile(): ProfileDTO
    suspend fun createProfile(profile: ProfileDTO)
}