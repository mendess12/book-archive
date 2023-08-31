package com.example.bookarchice.repository.di

import com.example.bookarchice.domain.repos.AuthRepository
import com.example.bookarchice.domain.repos.HomeRepository
import com.example.bookarchice.repository.AuthRepositoryImpl
import com.example.bookarchice.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}