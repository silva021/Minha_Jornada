package com.silva021.minhajornada.domain.usecases

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class LoginUseCase {
    suspend fun invoke(
        email: String,
        password: String
    ) = runCatching {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }
}
