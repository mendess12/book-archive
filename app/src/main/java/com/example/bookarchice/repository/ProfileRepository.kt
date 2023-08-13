package com.example.bookarchice.repository

import androidx.lifecycle.MutableLiveData
import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth


// TODO Repo katmanina Livedata(android componentleri girmez)
// TODO Firebase Auth Constructordan verilir(Dependency Injection)
// TODO Task donmektense, coroutine extension kutuphanesi eklenecek ve methodlar suspende cevrilecek
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
                    logDebug("User Message", "Re-Authentication success")

                    user.updatePassword(newPassword).addOnCompleteListener {
                        if (it.isSuccessful) {
                            changePasswordLiveData.postValue(it.toString())
                        } else {
                            changePasswordLiveData.postValue(null)
                        }
                    }.addOnFailureListener {
                        changePasswordLiveData.postValue(null)
                        logDebug("Change password failed message", it.message.toString())
                    }
                } else {
                    logDebug("Re-authenticate message failed", it.exception.toString())
                    changePasswordLiveData.postValue(null)
                }
            }
        }
    }
}