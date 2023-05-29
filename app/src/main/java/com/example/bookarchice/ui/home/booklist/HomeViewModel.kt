package com.example.bookarchice.ui.home.booklist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.model.Book
import com.example.bookarchice.repository.HomeRepository

class HomeViewModel : ViewModel() {

    private val homeRepository = HomeRepository()
    var booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()


    fun getBookDataFromRepository() = homeRepository.getBookList(booksLiveData)
}