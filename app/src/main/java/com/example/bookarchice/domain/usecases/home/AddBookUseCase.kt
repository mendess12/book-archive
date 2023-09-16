package com.example.bookarchice.domain.usecases.home

import com.example.bookarchice.domain.SuspendUseCase
import com.example.bookarchice.domain.repos.HomeRepository
import com.example.bookarchice.model.Book
import com.example.bookarchice.util.AppResult
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class AddBookUseCase @Inject constructor(private val homeRepository: HomeRepository) :
    SuspendUseCase<AddBookParams, AppResult<DocumentReference>>() {
    override suspend fun execute(params: AddBookParams): AppResult<DocumentReference> {
        return homeRepository.addBookData(params.book)
    }
}

data class AddBookParams(val book: Book)