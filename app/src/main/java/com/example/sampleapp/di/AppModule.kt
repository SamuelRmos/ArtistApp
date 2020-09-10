package com.example.sampleapp.di

import com.example.sampleapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class AppModule constructor(private val app: App) {

    @Singleton
    @Provides
    fun provideApp(): App = app
}