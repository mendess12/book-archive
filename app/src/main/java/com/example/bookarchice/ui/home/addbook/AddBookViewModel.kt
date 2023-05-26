package com.example.bookarchice.ui.home.addbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.repository.HomeRepository

class AddBookViewModel : ViewModel() {

    private val homeRepository = HomeRepository()
    var name: String = ""
    var author: String = ""
    var pageNumber: String = ""
    var language: String = ""
    var type: String = ""
    var addLiveData = MutableLiveData<String>()

    fun addBookDataFromFirebase() = homeRepository.addBookData(name,author,pageNumber,type,language, addLiveData)
}