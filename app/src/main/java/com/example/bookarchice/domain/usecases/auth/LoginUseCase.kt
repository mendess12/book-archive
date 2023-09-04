package com.example.bookarchice.domain.usecases.auth

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.AuthRepository
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) :
    SuspendUseCase<LoginParams, AuthResult>() {
    override suspend fun execute(params: LoginParams): AuthResult {
        return authRepository.login(params.email, params.password)
    }
}

data class LoginParams(val email: String, val password: String)