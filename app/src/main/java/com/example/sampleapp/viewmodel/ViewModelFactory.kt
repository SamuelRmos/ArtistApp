package com.example.sampleapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.App
import com.example.sampleapp.di.AppComponent
import com.example.sampleapp.persistence.ArtistDao
import com.example.sampleapp.persistence.FavoriteDao
import com.example.sampleapp.repository.ArtistApiRepository
import com.example.sampleapp.repository.ArtistRepository
import com.example.sampleapp.repository.FavoriteRepository
import com.example.sampleapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var app: App

    @Inject
    lateinit var artistApiRepository: ArtistApiRepository

    @Inject
    lateinit var artistRepository: ArtistRepository

    @Inject
    lateinit var favoriteRepository: FavoriteRepository

    init {
        val appComponent: AppComponent = App.appComponent
        appComponent.inject(this)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(
                userRepository,
                app
            ) as T

            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                artistApiRepository,
                Dispatchers.Main,
                Dispatchers.IO
            ) as T

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(
                favoriteRepository
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}