package com.example.bookarchice.domain


/**
 * Suspend olmayan fonksiyonlar icin
 */
abstract class UseCase<in P, R> {

    operator fun invoke(params: P): R = execute(params)

    @Throws(RuntimeException::class)
    protected abstract fun execute(params: P): R
}
