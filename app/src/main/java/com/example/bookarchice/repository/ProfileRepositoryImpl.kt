package com.example.bookarchice.repository

import com.example.bookarchice.domain.repos.ProfileRepository
import com.example.bookarchice.util.AppResult
import com.example.bookarchice.util.attempt
import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    ProfileRepository {
    override suspend fun changePassword(
        password: String,
        newPassword: String
    ): AppResult<Unit> {
        return attempt {
            val user = firebaseAuth.currentUser
            val userEmail = user?.email
            if (user != null && userEmail != null) {
                val credential = EmailAuthProvider.getCredential(userEmail, password)
                user.reauthenticate(credential).await()
                logDebug("User Message", "Re-Authentication success")
                user.updatePassword(newPassword).await()
            }
        }
    }
}