package com.example.bookarchice.repository

import com.example.bookarchice.domain.repos.AuthRepository
import com.example.bookarchice.util.AppResult
import com.example.bookarchice.util.attempt
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthRepository {

    override suspend fun login(email: String, password: String): AppResult<AuthResult> {
        return attempt {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }
    }

    override suspend fun register(email: String, password: String): AppResult<AuthResult> {
        return attempt {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }
    }

    override suspend fun forgotPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}