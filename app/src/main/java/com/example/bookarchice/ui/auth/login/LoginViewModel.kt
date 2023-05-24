package com.example.bookarchice.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.repository.AuthRepository

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()
    var loginLiveData = MutableLiveData<String>()
    var email: String = ""
    var password: String = ""

    fun getLoginDataFromRepository() = authRepository.login(email, password, loginLiveData)
}