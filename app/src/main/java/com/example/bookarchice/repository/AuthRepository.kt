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
}