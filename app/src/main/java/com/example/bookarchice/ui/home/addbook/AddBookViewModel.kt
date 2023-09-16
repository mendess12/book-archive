package com.example.bookarchice.ui.home.addbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.home.AddBookParams
import com.example.bookarchice.domain.usecases.home.AddBookUseCase
import com.example.bookarchice.model.Book
import com.example.bookarchice.util.AppResult
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(private val addBookUseCase: AddBookUseCase) :
    ViewModel() {

    var addLiveData = MutableLiveData<AppResult<DocumentReference>>()

    fun addBookDataFromFirebase(book: Book) {
        viewModelScope.launch {
            val result = addBookUseCase(AddBookParams(book))
            addLiveData.postValue(result)
        }
    }
}