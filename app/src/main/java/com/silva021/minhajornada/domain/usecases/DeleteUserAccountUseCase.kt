package com.silva021.minhajornada.domain.usecases

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class DeleteUserAccountUseCase {
    suspend fun invoke(
    ) = runCatching {
        val user = Firebase.auth.currentUser
        user?.delete()?.await()
    }
}
