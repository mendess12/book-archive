package com.example.bookarchice.ui.profile.changepassword

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.databinding.FragmentChangePasswordBinding
import com.example.bookarchice.repository.ProfileRepository
import com.example.bookarchice.util.showSnackBar

class ChangePasswordViewModel : ViewModel() {
    private val profileRepository = ProfileRepository()
    var changePasswordLiveData = MutableLiveData<String>()
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
        profileRepository.changePassword(password, newPassword, changePasswordLiveData)
    }
}