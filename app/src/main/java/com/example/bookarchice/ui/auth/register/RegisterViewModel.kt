package com.example.bookarchice.ui.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.repository.AuthRepository

class RegisterViewModel : ViewModel() {

    private val authRepository = AuthRepository()
    var registerLiveData = MutableLiveData<String>()
    var email: String = ""
    var password: String = ""
    var userName: String = ""

    fun getRegisterDataFromRepository() = authRepository.register(email, password, registerLiveData)
}