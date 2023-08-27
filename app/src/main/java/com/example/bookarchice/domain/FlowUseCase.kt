package com.example.bookarchice.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Flow tipinde deger donecek usecaseler icin
 */
abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    operator fun invoke(params: P): Flow<R> = execute(params)
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(params: P): Flow<R>
}