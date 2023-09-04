package com.example.bookarchice.domain.repos

import com.example.bookarchice.model.Book
import com.example.bookarchice.model.SuggestionBook
import com.google.firebase.firestore.DocumentReference

interface HomeRepository {

    suspend fun addBookData(book: Book): DocumentReference

    suspend fun getBookList(): List<Book>

    //TODO bunun icin use case
    suspend fun getSuggestionList(): List<SuggestionBook>
}