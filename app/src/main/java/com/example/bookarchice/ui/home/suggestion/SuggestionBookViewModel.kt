package com.example.bookarchice.ui.home.suggestion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.home.GetSuggestionUseCase
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.util.logDebug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionBookViewModel @Inject constructor(private val getSuggestionUseCase: GetSuggestionUseCase) :
    ViewModel() {

    var suggestionLiveData: MutableLiveData<List<SuggestionBook>?> = MutableLiveData()

    fun getSuggestionBookDataFromRepository() {
        viewModelScope.launch {
            try {
                val result = getSuggestionUseCase(Unit)
                suggestionLiveData.postValue(result)
            } catch (exception: Exception) {
                logDebug("Error Suggestion", exception.toString())
                suggestionLiveData.postValue(null)
            }
        }
    }

}