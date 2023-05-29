package com.example.bookarchice.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ProfileRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun changePassword(
        password: String,
        newPassword: String,
        changePasswordLiveData: MutableLiveData<String>
    ) {
        val user = auth.currentUser
        if (user != null && user.email != null) {
            val credential = EmailAuthProvider.getCredential(user.email!!, password)

            user.reauthenticate(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.e("User Message", "Re-Authentication success")

                    user.updatePassword(newPassword).addOnCompleteListener {
                        if (it.isSuccessful) {
                            changePasswordLiveData.postValue(it.toString())
                        } else {
                            changePasswordLiveData.postValue(null)
                        }
                    }.addOnFailureListener {
                        changePasswordLiveData.postValue(null)
                        Log.e("Change password failed message", it.message.toString())
                    }
                } else {
                    Log.e("Re-authenticate message failed", it.exception.toString())
                    changePasswordLiveData.postValue(null)
                }
            }
        }
    }
}