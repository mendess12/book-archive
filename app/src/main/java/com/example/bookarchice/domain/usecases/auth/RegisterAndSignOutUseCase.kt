package com.example.bookarchice.domain.usecases.auth

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.AuthRepository
import com.example.bookarchice.util.AppResult
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class RegisterAndSignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : SuspendUseCase<RegisterParams, AppResult<AuthResult>>() {
    override suspend fun execute(params: RegisterParams): AppResult<AuthResult> {
        //TODO email ve password kontrolleri burada yapilabilir
        val result = authRepository.register(params.email, params.password)
        authRepository.signOut()
        return result
    }
}

data class RegisterParams(val email: String, val password: String)