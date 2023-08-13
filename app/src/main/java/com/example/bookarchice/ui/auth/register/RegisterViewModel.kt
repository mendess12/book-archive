package com.example.bookarchice.ui.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {

    private val authRepository = AuthRepository(
        FirebaseAuth.getInstance()
    )
    var registerLiveData = MutableLiveData<String>()
    var email: String = ""
    var password: String = ""
    var userName: String = ""

    fun getRegisterDataFromRepository() = authRepository.register(email, password, registerLiveData)
}