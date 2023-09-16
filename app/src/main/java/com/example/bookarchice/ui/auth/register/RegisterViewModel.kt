package com.example.bookarchice.ui.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.auth.RegisterAndSignOutUseCase
import com.example.bookarchice.domain.usecases.auth.RegisterParams
import com.example.bookarchice.util.AppResult
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterAndSignOutUseCase
) : ViewModel() {

    var registerLiveData = MutableLiveData<AppResult<AuthResult>>()

    fun getRegisterDataFromRepository(email: String, password: String) {
        viewModelScope.launch {
            val result = registerUseCase(RegisterParams(email, password))
            registerLiveData.postValue(result)
        }
    }
}