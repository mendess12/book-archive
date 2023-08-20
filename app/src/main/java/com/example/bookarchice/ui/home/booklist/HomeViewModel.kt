package com.example.bookarchice.ui.home.booklist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.model.Book
import com.example.bookarchice.repository.HomeRepository
import com.example.bookarchice.util.logDebug
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val homeRepository = HomeRepository(FirebaseFirestore.getInstance())
    var booksLiveData: MutableLiveData<List<Book>?> = MutableLiveData()


    fun getBookDataFromRepository() {
        viewModelScope.launch {
            try {
                val result = homeRepository.getBookList()
                booksLiveData.postValue(result)
            } catch (exception: Exception) {
                booksLiveData.postValue(null)
                Log.e(TAG,"$exception")
                logDebug("Home message", exception.toString())
            }
        }
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }
}