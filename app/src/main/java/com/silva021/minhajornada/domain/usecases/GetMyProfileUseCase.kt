package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ProfileRepository
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.domain.model.toDomain

class GetMyProfileUseCase(
    val repository: ProfileRepository
) {
    suspend operator fun invoke() : Result<Profile> {
        return try {
            Result.success(repository.getProfile().toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}