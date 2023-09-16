package com.example.bookarchice.ui.home.suggestion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookarchice.domain.usecases.home.GetSuggestionUseCase
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.util.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionBookViewModel @Inject constructor(private val getSuggestionUseCase: GetSuggestionUseCase) :
    ViewModel() {

    var suggestionLiveData: MutableLiveData<AppResult<List<SuggestionBook>>> = MutableLiveData()

    fun getSuggestionBookDataFromRepository() {
        viewModelScope.launch {
            val result = getSuggestionUseCase(Unit)
            suggestionLiveData.postValue(result)
        }
    }
}