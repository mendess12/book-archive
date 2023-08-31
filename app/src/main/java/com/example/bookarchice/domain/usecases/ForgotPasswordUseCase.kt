package com.example.bookarchice.domain.usecases

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val authRepository: AuthRepository) :
    SuspendUseCase<ForgotPasswordParams, Unit>() {
    override suspend fun execute(params: ForgotPasswordParams) {
        authRepository.forgotPassword(params.email)
    }
}

data class ForgotPasswordParams(val email: String)