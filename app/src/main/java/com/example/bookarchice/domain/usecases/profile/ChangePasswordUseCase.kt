package com.example.bookarchice.domain.usecases.profile

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.ProfileRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : SuspendUseCase<ChangePasswordParams, Unit>() {
    override suspend fun execute(params: ChangePasswordParams) {
        return profileRepository.changePassword(params.password, params.newPassword)
    }

}

data class ChangePasswordParams(val password: String, val newPassword: String)