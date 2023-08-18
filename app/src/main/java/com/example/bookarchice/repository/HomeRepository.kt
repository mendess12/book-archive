package com.example.bookarchice.repository

import com.example.bookarchice.model.Book
import com.example.bookarchice.util.BookConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

// TODO Repo katmanina Livedata(android componentleri girmez)
// TODO Firebase Auth ve FirebaseFirestore Constructordan verilir(Dependency Injection)
// TODO Task donmektense, coroutine extension kutuphanesi eklenecek
class HomeRepository(private var database: FirebaseFirestore) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val const = BookConstants

    suspend fun addBookData(book: Book): DocumentReference {
        return database.collection(const.BOOKS).add(book).await()
    }

    suspend fun getBookList(): QuerySnapshot {
        val uid = auth.currentUser?.uid
        return database.collection(const.BOOKS).whereEqualTo("userId", uid).get().await()
    }

    suspend fun getSuggestionList(): QuerySnapshot {
        return database.collection(const.SUGGESTION).get().await()
    }
}