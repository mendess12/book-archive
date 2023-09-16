package com.example.bookarchice.domain.repos

import com.example.bookarchice.util.AppResult

interface ProfileRepository {

    suspend fun changePassword(password: String, newPassword: String): AppResult<Unit>
}