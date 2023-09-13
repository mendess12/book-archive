package com.example.bookarchice.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.auth.LoginParams
import com.example.bookarchice.domain.usecases.auth.LoginUseCase
import com.example.bookarchice.util.AppResult
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    val loginLiveData = MutableLiveData<AppResult<AuthResult>>()

    fun getLoginDataFromRepository(email: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase(LoginParams(email, password))
            loginLiveData.postValue(result)
        }
    }
}