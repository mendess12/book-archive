package com.example.bookarchice.ui.home.booklist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.repos.HomeRepository
import com.example.bookarchice.domain.usecases.home.GetBooksUseCase
import com.example.bookarchice.model.Book
import com.example.bookarchice.util.logDebug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getBooksUseCase: GetBooksUseCase) : ViewModel() {

    var booksLiveData: MutableLiveData<List<Book>?> = MutableLiveData()

    fun getBookDataFromRepository() {
        viewModelScope.launch {
            try {
                val result = getBooksUseCase(Unit)
                booksLiveData.postValue(result)
            } catch (exception: Exception) {
                booksLiveData.postValue(null)
                Log.e(TAG, "$exception")
                logDebug("Home message", exception.toString())
            }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}