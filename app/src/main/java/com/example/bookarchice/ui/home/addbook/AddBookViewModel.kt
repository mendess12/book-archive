package com.example.bookarchice.ui.home.addbook

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.model.Book
import com.example.bookarchice.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.lang.Exception

//TODO Butun viewmodellara Dagger Hilt eklendikten sonra constructor injection yapilacak
class AddBookViewModel : ViewModel() {

    private val homeRepository = HomeRepository(FirebaseFirestore.getInstance())

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