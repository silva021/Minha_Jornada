package com.silva021.minhajornada.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.ProfileDTO
import kotlinx.coroutines.tasks.await

class ProfileRepositoryImpl() : ProfileRepository {
    val usersCollection by lazy { FireStoreHelper.usersCollection }

    override suspend fun getProfile(): ProfileDTO {
        return usersCollection.document(Firebase.auth.uid.orEmpty()).get().await()
            .toObject(ProfileDTO::class.java) ?: throw Exception("Profile not found")
    }

    override suspend fun createProfile(profile: ProfileDTO) {
        usersCollection.document(profile.id).set(profile).await()
    }
}

interface ProfileRepository {
    suspend fun getProfile(): ProfileDTO
    suspend fun createProfile(profile: ProfileDTO)
}