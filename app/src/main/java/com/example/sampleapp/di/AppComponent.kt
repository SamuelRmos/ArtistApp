package com.example.sampleapp.di

import com.example.sampleapp.repository.ArtistApiRepository
import com.example.sampleapp.repository.ArtistRepository
import com.example.sampleapp.repository.FavoriteRepository
import com.example.sampleapp.view.adapter.ArtistAdapter
import com.example.sampleapp.viewmodel.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, UserRepositoryModule::class,
        ApiModule::class, PersistenceDataModule::class, RepositoryModule::class]
)

interface AppComponent {
    fun inject(viewModelFactory: ViewModelFactory)
    fun inject(favoriteRepository: FavoriteRepository)
    fun inject(artistRepository: ArtistRepository)
    fun inject(artistAdapter: ArtistAdapter)
}