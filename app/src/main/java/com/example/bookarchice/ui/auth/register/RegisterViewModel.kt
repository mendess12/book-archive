package com.example.bookarchice.ui.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.repository.AuthRepository
import com.example.bookarchice.util.logDebug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val registerLogTitle = "Error Register"
    var registerLiveData = MutableLiveData<String?>()

    fun getRegisterDataFromRepository(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = authRepository.register(email, password)
                registerLiveData.postValue(result.toString())
            } catch (exception: Exception) {
                logDebug(registerLogTitle, exception.toString())
                registerLiveData.postValue(null)
            }
        }
    }
}