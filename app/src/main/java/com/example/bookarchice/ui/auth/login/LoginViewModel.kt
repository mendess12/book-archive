package com.example.bookarchice.ui.auth.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.databinding.FragmentLoginBinding
import com.example.bookarchice.repository.AuthRepository
import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginLogTitle = "Error Login"
    private val authRepository = AuthRepository(
        FirebaseAuth.getInstance()
    )
    var loginLiveData = MutableLiveData<String?>()

    fun getLoginDataFromRepository(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = authRepository.login(email, password)
                loginLiveData.postValue(result.toString())
            }catch (exception: Exception){
                logDebug(loginLogTitle, exception.toString())
                loginLiveData.postValue(null)
            }
        }
    }
}