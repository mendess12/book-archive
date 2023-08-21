package com.example.bookarchice.repository

import com.example.bookarchice.model.Book
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.util.BookConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// Dagger Hilt takilarak Butun Repositorylere fieldlar ordan verilecek
class HomeRepository(private var database: FirebaseFirestore) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun addBookData(book: Book): DocumentReference {
        return database.collection(BookConstants.BOOKS).add(book).await()
    }

    suspend fun getBookList(): List<Book> {
        val uid = auth.currentUser?.uid
        return database.collection(BookConstants.BOOKS).whereEqualTo("userId", uid)
            .get()
            .await()
            .toObjects(Book::class.java)
    }

    suspend fun getSuggestionList(): List<SuggestionBook> {
        return database.collection(BookConstants.SUGGESTION)
            .get()
            .await()
            .toObjects(SuggestionBook::class.java)
    }
}