package com.silva021.minhajornada.domain.usecases

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LogoutUserUseCase {
    fun invoke() = runCatching {
        Firebase.auth.signOut()
    }
}
