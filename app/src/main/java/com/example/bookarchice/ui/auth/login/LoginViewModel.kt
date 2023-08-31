package com.example.bookarchice.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.LoginParams
import com.example.bookarchice.domain.usecases.LoginUseCase
import com.example.bookarchice.util.logDebug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val loginLogTitle = "Error Login"
    var loginLiveData = MutableLiveData<String?>()

    fun getLoginDataFromRepository(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = loginUseCase(LoginParams(email, password))
                loginLiveData.postValue(result.toString())
            } catch (exception: Exception) {
                logDebug(loginLogTitle, exception.toString())
                loginLiveData.postValue(null)
            }
        }
    }
}