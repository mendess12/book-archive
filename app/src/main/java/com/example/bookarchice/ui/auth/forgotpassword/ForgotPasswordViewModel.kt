package com.example.bookarchice.ui.auth.forgotpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.auth.ForgotPasswordParams
import com.example.bookarchice.domain.usecases.auth.ForgotPasswordUseCase
import com.example.bookarchice.util.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val forgotPasswordUseCase: ForgotPasswordUseCase) :
    ViewModel() {
    
    var forgotPasswordLiveData = MutableLiveData<AppResult<Unit>>()

    fun getForgotPasswordDataFromRepository(email: String) {
        viewModelScope.launch {
            val result = forgotPasswordUseCase(ForgotPasswordParams(email))
            forgotPasswordLiveData.postValue(result)
        }
    }
}