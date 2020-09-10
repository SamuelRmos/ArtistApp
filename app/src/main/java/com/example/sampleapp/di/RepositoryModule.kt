package com.example.sampleapp.di

import com.example.sampleapp.network.ArtistApi
import com.example.sampleapp.persistence.ArtistDao
import com.example.sampleapp.repository.ArtistApiRepository
import com.example.sampleapp.repository.ArtistRepository
import com.example.sampleapp.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFavoriteRepository(): FavoriteRepository = FavoriteRepository()

    @Singleton
    @Provides
    fun provideArtistRepository(artistDao: ArtistDao): ArtistRepository =
        ArtistRepository(artistDao)

    @Singleton
    @Provides
    fun provideArtistApiRepository(
        artistApi: ArtistApi,
        artistRepository: ArtistRepository
    ): ArtistApiRepository = ArtistApiRepository(artistApi, artistRepository)
}