package com.example.sampleapp.di

import com.example.sampleapp.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserRepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(): UserRepository = UserRepository()
}