package com.example.bookarchice.domain.repos

import com.example.bookarchice.model.Book
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.util.AppResult
import com.google.firebase.firestore.DocumentReference

interface HomeRepository {

    suspend fun addBookData(book: Book): AppResult<DocumentReference>

    suspend fun getBookList(): AppResult<List<Book>>

    //TODO bunun icin use case
    suspend fun getSuggestionList(): AppResult<List<SuggestionBook>>
}