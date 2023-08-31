package com.example.bookarchice.ui.home.addbook

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.homeusecases.AddBookParams
import com.example.bookarchice.domain.usecases.homeusecases.AddBookUseCase
import com.example.bookarchice.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(private val addBookUseCase: AddBookUseCase) :
    ViewModel() {

    var addLiveData = MutableLiveData<String?>()

    fun addBookDataFromFirebase(book: Book) {
        viewModelScope.launch {
            try {
                val result = addBookUseCase(AddBookParams(book))
                addLiveData.postValue(result.toString())

            } catch (exception: Exception) {
                Log.e("Add book error", exception.toString())
                addLiveData.postValue(null)
            }
        }
    }
}