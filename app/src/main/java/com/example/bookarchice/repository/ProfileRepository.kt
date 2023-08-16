package com.example.bookarchice.repository

import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class ProfileRepository(val auth: FirebaseAuth) {
    suspend fun changePassword(
        password: String,
        newPassword: String
    ) {
        val user = auth.currentUser
        if (user != null && user.email != null) {
            val credential = EmailAuthProvider.getCredential(user.email!!, password)
            user.reauthenticate(credential).await()
            logDebug("User Message", "Re-Authentication success")
            user.updatePassword(newPassword).await()
        }
    }

}