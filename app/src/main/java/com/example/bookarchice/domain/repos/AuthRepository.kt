package com.example.bookarchice.domain.repos

import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult

    suspend fun register(email: String, password: String): AuthResult

    suspend fun forgotPassword(email: String)

    suspend fun signOut()
}