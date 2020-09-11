package com.example.sampleapp.di

import com.example.sampleapp.network.ArtistApi
import com.example.sampleapp.persistence.ArtistDao
import com.example.sampleapp.repository.ArtistApiRepository
import com.example.sampleapp.repository.ArtistDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideArtistDataRepository(artistDao: ArtistDao) = ArtistDataRepository(artistDao)

    @Singleton
    @Provides
    fun provideArtistApiRepository(
            artistApi: ArtistApi,
            artistDataRepository: ArtistDataRepository
    ): ArtistApiRepository = ArtistApiRepository(artistApi, artistDataRepository)
}