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

    // TODO Burasi direk objeye parse olacak -> yapıldı
    fun getSuggestionBookDataFromRepository() {
        viewModelScope.launch {
            try {
                val result = homeRepository.getSuggestionList()
                suggestionLiveData.postValue(result)
            } catch (exception: Exception) {
                logDebug("Error Suggestion", exception.toString())
                suggestionLiveData.postValue(null)
            }
        }
    }

}