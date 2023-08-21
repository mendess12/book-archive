package com.example.bookarchice.ui.home.suggestion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.repository.HomeRepository
import com.example.bookarchice.util.logDebug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionBookViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

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