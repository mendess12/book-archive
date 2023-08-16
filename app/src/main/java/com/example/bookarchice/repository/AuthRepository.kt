package com.example.bookarchice.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository(private val firebaseAuth: FirebaseAuth) {

    suspend fun login(email: String, password: String): AuthResult{
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun register(email: String, password: String): AuthResult {
        val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        firebaseAuth.signOut()
        return authResult
    }

    suspend fun forgotPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }
}