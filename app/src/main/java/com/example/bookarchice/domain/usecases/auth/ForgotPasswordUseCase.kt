package com.example.bookarchice.domain.usecases.auth

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.AuthRepository
import com.example.bookarchice.util.AppResult
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val authRepository: AuthRepository) :
    SuspendUseCase<ForgotPasswordParams, AppResult<Unit>>() {
    override suspend fun execute(params: ForgotPasswordParams) : AppResult<Unit> {
        return authRepository.forgotPassword(params.email)
    }
}

data class ForgotPasswordParams(val email: String)