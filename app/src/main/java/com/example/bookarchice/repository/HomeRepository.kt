package com.example.bookarchice.repository

import com.example.bookarchice.di.NetworkModule
import com.example.bookarchice.model.Book
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.util.BookConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// Dagger Hilt takilarak Butun Repositorylere fieldlar ordan verilecek
class HomeRepository @Inject constructor(private var networkModule: NetworkModule) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun addBookData(book: Book): DocumentReference {
        return networkModule.provideFirebaseDataStore().collection(BookConstants.BOOKS).add(book)
            .await()
    }

    suspend fun getBookList(): List<Book> {
        val uid = auth.currentUser?.uid
        return networkModule.provideFirebaseDataStore().collection(BookConstants.BOOKS)
            .whereEqualTo("userId", uid)
            .get()
            .await()
            .toObjects(Book::class.java)
    }

    suspend fun getSuggestionList(): List<SuggestionBook> {
        return networkModule.provideFirebaseDataStore().collection(BookConstants.SUGGESTION)
            .get()
            .await()
            .toObjects(SuggestionBook::class.java)
    }
}