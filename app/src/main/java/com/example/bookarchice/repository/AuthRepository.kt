package com.example.bookarchice.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private lateinit var auth: FirebaseAuth

    fun login(email: String, password: String, loginLiveData: MutableLiveData<String>) {
        auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                loginLiveData.postValue(it.toString())
            } else {
                Log.e("Error login", it.exception.toString())
                loginLiveData.postValue(null)
            }
        }.addOnFailureListener {
            loginLiveData.postValue(null)
            Log.e("Error login", it.toString())
        }
    }

    fun register(email: String, password: String, registerLiveData: MutableLiveData<String>) {
        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                registerLiveData.postValue(it.toString())
            } else {
                Log.e("Error register", it.exception.toString())
                registerLiveData.postValue(null)
            }
        }.addOnFailureListener {
            registerLiveData.postValue(null)
            Log.e("Error register", it.toString())
        }
    }

    fun forgotPassword(email: String, forgotPasswordLiveData: MutableLiveData<String>) {
        auth = FirebaseAuth.getInstance()

        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                forgotPasswordLiveData.postValue(it.toString())
            } else {
                Log.e("Error forgot password", it.exception.toString())
                forgotPasswordLiveData.postValue(null)
            }
        }.addOnFailureListener {
            forgotPasswordLiveData.postValue(null)
            Log.e("Error forgot password", it.toString())
        }
    }
}