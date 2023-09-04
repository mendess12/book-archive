package com.example.bookarchice.domain.usecases.home

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.HomeRepository
import com.example.bookarchice.model.Book
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val homeRepository: HomeRepository) :
    SuspendUseCase<Unit, List<Book>>() {
    override suspend fun execute(params: Unit): List<Book> {
        return homeRepository.getBookList()
    }
}