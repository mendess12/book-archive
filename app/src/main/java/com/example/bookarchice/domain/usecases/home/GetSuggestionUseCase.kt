package com.example.bookarchice.domain.usecases.home

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.HomeRepository
import com.example.bookarchice.model.SuggestionBook
import javax.inject.Inject

class GetSuggestionUseCase @Inject constructor(private val homeRepository: HomeRepository) :
    SuspendUseCase<Unit, List<SuggestionBook>>() {
    override suspend fun execute(params: Unit): List<SuggestionBook> {
        return homeRepository.getSuggestionList()
    }
}