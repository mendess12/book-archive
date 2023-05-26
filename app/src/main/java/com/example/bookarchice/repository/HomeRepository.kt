package com.example.bookarchice.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookarchice.model.Book
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeRepository {

    private lateinit var database: FirebaseFirestore

    fun addBookData(
        name: String,
        author: String,
        pageNumber: String,
        type: String,
        language: String,
        addLiveData: MutableLiveData<String>
    ) {
        val uuid = FirebaseAuth.getInstance().currentUser?.uid
        database = FirebaseFirestore.getInstance()

        val book = Book(name, author, pageNumber, type, language, uuid.toString())

        database.collection("Books").add(book).addOnCompleteListener {
            if (it.isSuccessful) {
                addLiveData.postValue("not null")
                Log.e("Add book success message", it.exception.toString())
            } else {
                addLiveData.postValue(null)
                Log.e("Add book not success message", it.exception.toString())
            }
        }.addOnFailureListener {
            addLiveData.postValue(null)
            Log.e("Add book failed message", it.toString())
        }
    }
}