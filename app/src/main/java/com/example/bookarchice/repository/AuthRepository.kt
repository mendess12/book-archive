package com.example.bookarchice.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

// TODO Repo katmanina Livedata(android componentleri girmez)
// TODO Firebase Auth Constructordan verilir(Dependency Injection)
// TODO Task donmektense, coroutine extension kutuphanesi eklenecek
class AuthRepository(private val firebaseAuth: FirebaseAuth) {

    suspend fun login(email: String, password: String): AuthResult{
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun register(email: String, password: String): AuthResult {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun forgotPassword(email: String): Void {
        return firebaseAuth.sendPasswordResetEmail(email).await()
    }
}