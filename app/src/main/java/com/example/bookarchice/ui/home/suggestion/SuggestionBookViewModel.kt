package com.example.bookarchice.ui.home.suggestion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookarchice.model.SuggestionBook
import com.example.bookarchice.repository.HomeRepository

class SuggestionBookViewModel : ViewModel() {

    private val homeRepository = HomeRepository()
    var suggestionLiveData: MutableLiveData<List<SuggestionBook>> = MutableLiveData()

    fun getSuggestionBookDataFromRepository() = homeRepository.getSuggestionsList(suggestionLiveData)
}