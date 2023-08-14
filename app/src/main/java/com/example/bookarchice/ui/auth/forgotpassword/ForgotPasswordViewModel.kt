package com.example.bookarchice.ui.auth.forgotpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.repository.AuthRepository
import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.lang.Exception

class ForgotPasswordViewModel : ViewModel() {

    private val forgotPasswordLogTitle = "Error Forgot Password"
    private val authRepository = AuthRepository(
        FirebaseAuth.getInstance()
    )
    var forgotPasswordLiveData = MutableLiveData<String?>()

    fun getForgotPasswordDataFromRepository(email: String) {
        viewModelScope.launch {
            try {
                val result = authRepository.forgotPassword(email)
                forgotPasswordLiveData.postValue(result.toString())
            } catch (exception: Exception) {
                logDebug(forgotPasswordLogTitle, exception.toString())
                forgotPasswordLiveData.postValue(null)
            }
        }
    }
}