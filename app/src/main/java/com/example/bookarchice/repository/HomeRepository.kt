package com.example.bookarchice.repository

import androidx.lifecycle.MutableLiveData
import com.example.bookarchice.model.Book
import com.example.bookarchice.util.logDebug
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

// TODO Repo katmanina Livedata(android componentleri girmez)
// TODO Firebase Auth ve FirebaseFirestore Constructordan verilir(Dependency Injection)
// TODO Task donmektense, coroutine extension kutuphanesi eklenecek
class HomeRepository(private var database: FirebaseFirestore) {

    //private lateinit var database: FirebaseFirestore
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun addBookData(
        name: String,
        author: String,
        pageNumber: String,
        type: String,
        language: String,
        publisher: String,
        message: String,
        date: String,
        addLiveData: MutableLiveData<String>
    ) {
        val uuid = FirebaseAuth.getInstance().currentUser?.uid
        database = FirebaseFirestore.getInstance()

        val book = Book(
            name,
            author,
            pageNumber,
            type,
            language,
            publisher,
            message,
            date,
            uuid.toString()
        )
        database.collection("Books").add(book).addOnCompleteListener {
            if (it.isSuccessful) {
                addLiveData.postValue("not null")
                logDebug("Add book success message", it.exception.toString())
            } else {
                addLiveData.postValue(null)
                logDebug("Add book not success message", it.exception.toString())
            }
        }.addOnFailureListener {
            addLiveData.postValue(null)
            logDebug("Add book failed message", it.toString())
        }
    }


    /* fun getBookList(
         bookListLiveData: MutableLiveData<List<Book>>
     ) {
         database = FirebaseFirestore.getInstance()
         val uid = auth.currentUser?.uid
         // TODO bu magic stringler constanta alinmali, ve Document direk Objeye cevrilebilir
         database.collection("Books").whereEqualTo("userId", uid).get().addOnSuccessListener {
             val bookList = mutableListOf<Book>()
             for (document in it) {
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
             bookListLiveData.value = bookList
         }.addOnFailureListener {
             bookListLiveData.value = null
             logDebug("Failed message", it.message.toString())
         }
     }*/

    suspend fun getBookList(): QuerySnapshot {
        val uid = auth.currentUser?.uid
        return database.collection("Books").whereEqualTo("userId", uid).get().await()
    }

    /* fun getSuggestionsList(
         suggestionsListLiveData: MutableLiveData<List<SuggestionBook>>
     ) {
         database = FirebaseFirestore.getInstance()

         database.collection("Suggestion").get().addOnSuccessListener {
             val suggestionList = mutableListOf<SuggestionBook>()
             for (document in it) {
                 val name = document.getString("name")
                 val author = document.getString("author")
                 val pageNumber = document.getString("subject")
                 val type = document.getString("type")

                 suggestionList.add(
                     SuggestionBook(
                         name,
                         author,
                         pageNumber,
                         type
                     )
                 )
             }
             suggestionsListLiveData.value = suggestionList
         }.addOnFailureListener {
             suggestionsListLiveData.value = null
             logDebug("Failed message", it.message.toString())
         }
     }*/

    suspend fun getSuggestionList(): QuerySnapshot {
        return database.collection("Suggestion").get().await()
    }
}