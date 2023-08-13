package com.example.bookarchice.ui.auth.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.databinding.FragmentLoginBinding
import com.example.bookarchice.repository.AuthRepository

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()
    var loginLiveData = MutableLiveData<String>()

    fun getLoginDataFromRepository(binding: FragmentLoginBinding, email: String, password: String) {

        if (email.isEmpty()) {
            binding.loginScreenEmailEt.error = "Email required"
            binding.loginScreenEmailEt.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.loginScreenEmailEt.error = "Valid email required"
            binding.loginScreenEmailEt.requestFocus()
            return
        }
        if (password.isEmpty() || password.length < 6) {
            binding.loginScreenPasswordEt.error = "6 char password required"
            binding.loginScreenPasswordEt.requestFocus()
            return
        }
        authRepository.login(email, password, loginLiveData)
    }
}