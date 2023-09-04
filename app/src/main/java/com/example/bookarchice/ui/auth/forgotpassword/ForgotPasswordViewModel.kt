package com.example.bookarchice.ui.auth.forgotpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.auth.ForgotPasswordParams
import com.example.bookarchice.domain.usecases.auth.ForgotPasswordUseCase
import com.example.bookarchice.util.logDebug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val forgotPasswordUseCase: ForgotPasswordUseCase) :
    ViewModel() {

    private val forgotPasswordLogTitle = "Error Forgot Password"
    var forgotPasswordLiveData = MutableLiveData<String?>()

    fun getForgotPasswordDataFromRepository(email: String) {
        viewModelScope.launch {
            try {
                val result = forgotPasswordUseCase(ForgotPasswordParams(email))
                forgotPasswordLiveData.postValue(result.toString())
            } catch (exception: Exception) {
                logDebug(forgotPasswordLogTitle, exception.toString())
                forgotPasswordLiveData.postValue(null)
            }
        }
    }
}