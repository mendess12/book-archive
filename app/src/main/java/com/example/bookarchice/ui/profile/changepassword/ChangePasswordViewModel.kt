package com.example.bookarchice.ui.profile.changepassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.repository.ProfileRepository

class ChangePasswordViewModel : ViewModel() {
    private val profileRepository = ProfileRepository()
    var changePasswordLiveData = MutableLiveData<String>()
    var password: String = ""
    var newPassword: String = ""

    fun getChangePasswordDataFromRepository() = profileRepository.changePassword(password,newPassword, changePasswordLiveData)

}