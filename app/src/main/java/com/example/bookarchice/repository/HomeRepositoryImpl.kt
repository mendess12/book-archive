package com.example.bookarchice.repository

import com.example.bookarchice.domain.repos.HomeRepository
import com.example.bookarchice.model.Book
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.util.AppResult
import com.example.bookarchice.util.BookConstants
import com.example.bookarchice.util.attempt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val firebaseDataStore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : HomeRepository {

    override suspend fun addBookData(book: Book): AppResult<DocumentReference> {
        return attempt {
            firebaseDataStore.collection(BookConstants.BOOKS)
                .add(book)
                .await()
        }
    }

    override suspend fun getBookList(): AppResult<List<Book>> {
        val uid = auth.currentUser?.uid
        return attempt {
            firebaseDataStore.collection(BookConstants.BOOKS)
                .whereEqualTo("userId", uid)
                .get()
                .await()
                .toObjects(Book::class.java)
        }
    }

    override suspend fun getSuggestionList(): AppResult<List<SuggestionBook>> {
        return attempt {
            firebaseDataStore.collection(BookConstants.SUGGESTION)
                .get()
                .await()
                .toObjects(SuggestionBook::class.java)
        }
    }
}