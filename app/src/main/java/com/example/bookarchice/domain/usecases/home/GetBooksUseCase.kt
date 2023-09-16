package com.example.bookarchice.domain.usecases.home

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.HomeRepository
import com.example.bookarchice.model.Book
import com.example.bookarchice.util.AppResult
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val homeRepository: HomeRepository) :
    SuspendUseCase<Unit, AppResult<List<Book>>>() {
    override suspend fun execute(params: Unit): AppResult<List<Book>> {
        return homeRepository.getBookList()
    }
}