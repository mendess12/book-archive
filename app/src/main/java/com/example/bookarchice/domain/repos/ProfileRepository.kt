package com.example.bookarchice.domain.repos

interface ProfileRepository {

    suspend fun changePassword(password: String, newPassword: String)
}