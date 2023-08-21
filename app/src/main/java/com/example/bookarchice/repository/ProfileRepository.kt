package com.example.bookarchice.repository

import com.example.bookarchice.di.NetworkModule
import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.EmailAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val networkModule: NetworkModule) {
    suspend fun changePassword(
        password: String,
        newPassword: String
    ) {
        val user = networkModule.provideFirebaseAuth().currentUser
        val userEmail = user?.email
        if (user != null && userEmail != null) {
            val credential = EmailAuthProvider.getCredential(userEmail, password)
            user.reauthenticate(credential).await()
            logDebug("User Message", "Re-Authentication success")
            user.updatePassword(newPassword).await()
        }
    }

}