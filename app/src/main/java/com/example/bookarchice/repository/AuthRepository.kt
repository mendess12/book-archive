package com.example.bookarchice.repository

import androidx.lifecycle.MutableLiveData
import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.FirebaseAuth

// TODO Repo katmanina Livedata(android componentleri girmez)
// TODO Firebase Auth Constructordan verilir(Dependency Injection)
// TODO Task donmektense, coroutine extension kutuphanesi eklenecek
class AuthRepository {

    private lateinit var auth: FirebaseAuth
    private val loginLogTitle = "Error Login"
    private val registerLogTitle = "Error Register"
    private val forgotPasswordLogTitle = "Error Forgot Password"
    fun login(email: String, password: String, loginLiveData: MutableLiveData<String>) {
        auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                loginLiveData.postValue(it.toString())
            } else {
                logDebug(loginLogTitle, it.exception.toString())
                loginLiveData.postValue(null)
            }
        }.addOnFailureListener {
            loginLiveData.postValue(null)
            logDebug(loginLogTitle, it.localizedMessage!!)
        }
    }

    fun register(email: String, password: String, registerLiveData: MutableLiveData<String>) {
        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
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
        auth = FirebaseAuth.getInstance()

        auth.sendPasswordResetEmail(email).addOnCompleteListener {
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