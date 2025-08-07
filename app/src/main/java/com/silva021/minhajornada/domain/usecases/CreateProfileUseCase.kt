package com.silva021.minhajornada.domain.usecases

import com.google.firebase.Firebase
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.silva021.minhajornada.data.dto.toDTO
import com.silva021.minhajornada.data.repository.ProfileRepository
import com.silva021.minhajornada.domain.model.Profile
import kotlinx.coroutines.tasks.await

class CreateProfileUseCase(
    val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(
        profile: Profile,
        password: String,
    ) = runCatching {
        val authResult = Firebase
            .auth
            .createUserWithEmailAndPassword(profile.email, password)
            .await()

        val user = authResult.user ?: throw Exception("Usuário não encontrado")

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(profile.name)
            .build()

        user.updateProfile(profileUpdates).await()

        profileRepository.createProfile(
            profile.copy(id = user.uid).toDTO()
        )
    }
}
