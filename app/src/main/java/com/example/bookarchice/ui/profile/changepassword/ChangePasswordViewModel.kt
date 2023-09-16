package com.example.bookarchice.ui.profile.changepassword

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.databinding.FragmentChangePasswordBinding
import com.example.bookarchice.domain.usecases.profile.ChangePasswordParams
import com.example.bookarchice.domain.usecases.profile.ChangePasswordUseCase
import com.example.bookarchice.util.AppResult
import com.example.bookarchice.util.showSnackBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val changePasswordUseCase: ChangePasswordUseCase) :
    ViewModel() {

    var changePasswordErrorLiveData = MutableLiveData<AppResult<Unit>>()
    private val message = "6 char password required"

    fun getChangePasswordDataFromRepository(
        view: View,
        binding: FragmentChangePasswordBinding,
        password: String,
        newPassword: String,
        retypeNewPassword: String
    ) {
        if (password.isEmpty() || password.length < 6) {
            binding.changePasswordScreenPasswordEt.error = message
            binding.changePasswordScreenPasswordEt.requestFocus()
            return
        }
        if (newPassword.isEmpty() || newPassword.length < 6) {
            binding.changePasswordScreenNewPasswordEt.error = message
            binding.changePasswordScreenNewPasswordEt.requestFocus()
            return
        }
        if (retypeNewPassword.isEmpty() || retypeNewPassword.length < 6) {
            binding.changePasswordScreenRetypeNewPasswordEt.error = message
            binding.changePasswordScreenRetypeNewPasswordEt.requestFocus()
            return
        }
        if (newPassword != retypeNewPassword) {
            view.showSnackBar("New password and retype new password not same!")
            return
        }

        viewModelScope.launch {
            val result = changePasswordUseCase(ChangePasswordParams(password, newPassword))
            changePasswordErrorLiveData.postValue(result)

        }
    }
}