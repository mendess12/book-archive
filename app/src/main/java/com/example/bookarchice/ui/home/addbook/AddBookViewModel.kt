package com.example.bookarchice.ui.home.addbook

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.model.Book
import com.example.bookarchice.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {

    var addLiveData = MutableLiveData<String?>()

    fun addBookDataFromFirebase(book: Book) {
        viewModelScope.launch {
            try {
                val result = homeRepository.addBookData(book)
                addLiveData.postValue(result.toString())

            } catch (exception: Exception) {
                Log.e("Add book error", exception.toString())
                addLiveData.postValue(null)
            }
        }
    }
}