package com.example.bookarchice.ui.home.suggestion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.repository.HomeRepository
import com.example.bookarchice.util.logDebug
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class SuggestionBookViewModel : ViewModel() {

    private val homeRepository = HomeRepository(FirebaseFirestore.getInstance())
    var suggestionLiveData: MutableLiveData<List<SuggestionBook>?> = MutableLiveData()

    fun getSuggestionBookDataFromRepository() {
        viewModelScope.launch {
            try {
                val result = homeRepository.getSuggestionList()
                val suggestionList = mutableListOf<SuggestionBook>()
                for (document in result) {
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
                suggestionLiveData.postValue(suggestionList)
            } catch (exception: Exception) {
                logDebug("Error Suggestion", exception.toString())
                suggestionLiveData.postValue(null)
            }
        }
    }

}