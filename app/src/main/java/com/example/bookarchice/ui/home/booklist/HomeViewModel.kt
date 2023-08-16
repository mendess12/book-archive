package com.example.bookarchice.ui.home.booklist

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
                val bookList = mutableListOf<Book>()
                for (document in result) {
                    val name = document.getString("bookName")
                    val author = document.getString("bookAuthor")
                    val pageNumber = document.getString("pageNumber")
                    val type = document.getString("bookType")
                    val language = document.getString("bookLanguage")
                    val publisher = document.getString("bookPublisher")
                    val message = document.getString("bookMessage")
                    val date = document.getString("bookDate")
                    val userId = document.getString("userId")

                    bookList.add(
                        Book(
                            name,
                            author,
                            pageNumber,
                            type,
                            language,
                            publisher,
                            message,
                            date,
                            userId.toString()
                        )
                    )
                }
                booksLiveData.postValue(bookList)
            } catch (exception: Exception) {
                booksLiveData.postValue(null)
                logDebug("Home message", exception.toString())
            }
        }
    }
}