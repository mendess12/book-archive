package com.example.bookarchice.repository

import com.example.bookarchice.di.NetworkModule
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val networkModule: NetworkModule) {

    suspend fun login(email: String, password: String): AuthResult {
        return networkModule.provideFirebaseAuth().signInWithEmailAndPassword(email, password).await()
    }

    suspend fun register(email: String, password: String): AuthResult {
        val authResult = networkModule.provideFirebaseAuth().createUserWithEmailAndPassword(email, password).await()
        networkModule.provideFirebaseAuth().signOut()
        return authResult
    }

    suspend fun forgotPassword(email: String) {
        networkModule.provideFirebaseAuth().sendPasswordResetEmail(email).await()
    }
}