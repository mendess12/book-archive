package com.example.bookarchice.ui.home.booklist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.home.GetBooksUseCase
import com.example.bookarchice.model.Book
import com.example.bookarchice.util.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getBooksUseCase: GetBooksUseCase) :
    ViewModel() {

    var booksLiveData: MutableLiveData<AppResult<List<Book>>> = MutableLiveData()

    fun getBookDataFromRepository() {
        viewModelScope.launch {
            val result = getBooksUseCase(Unit)
            booksLiveData.postValue(result)
        }
    }
}