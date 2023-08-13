package com.example.bookarchice.ui.auth.forgotpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordViewModel : ViewModel() {

    private val authRepository = AuthRepository(
        FirebaseAuth.getInstance()
    )
    var email: String = ""
    var forgotPasswordLiveData = MutableLiveData<String>()

    fun getForgotPasswordDataFromRepository() =
        authRepository.forgotPassword(email, forgotPasswordLiveData)
}