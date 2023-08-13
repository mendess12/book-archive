package com.example.bookarchice.repository

import androidx.lifecycle.MutableLiveData
import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

// TODO Repo katmanina Livedata(android componentleri girmez)
// TODO Firebase Auth Constructordan verilir(Dependency Injection)
// TODO Task donmektense, coroutine extension kutuphanesi eklenecek
class AuthRepository(val firebaseAuth: FirebaseAuth) {

    private val loginLogTitle = "Error Login"
    private val registerLogTitle = "Error Register"
    private val forgotPasswordLogTitle = "Error Forgot Password"

    suspend fun login(email: String, password: String): AuthResult{
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    fun register(email: String, password: String, registerLiveData: MutableLiveData<String>) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                registerLiveData.postValue(it.toString())
            } else {
                logDebug(registerLogTitle, it.exception.toString())
                registerLiveData.postValue(null)
            }
        }.addOnFailureListener {
            registerLiveData.postValue(null)
            logDebug(registerLogTitle, it.localizedMessage!!)
        }
    }

    fun forgotPassword(email: String, forgotPasswordLiveData: MutableLiveData<String>) {

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                forgotPasswordLiveData.postValue(it.toString())
            } else {
                logDebug(forgotPasswordLogTitle, it.exception.toString())
                forgotPasswordLiveData.postValue(null)
            }
        }.addOnFailureListener {
            forgotPasswordLiveData.postValue(null)
            logDebug(forgotPasswordLogTitle, it.localizedMessage!!)
        }
    }
}