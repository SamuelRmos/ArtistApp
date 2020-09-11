package com.example.sampleapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.App
import com.example.sampleapp.di.AppComponent
import com.example.sampleapp.repository.ArtistApiRepository
import com.example.sampleapp.repository.ArtistDataRepository
import com.example.sampleapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var app: App

    @Inject
    lateinit var artistApiRepository: ArtistApiRepository

    @Inject
    lateinit var artistDataRepository: ArtistDataRepository

    init {
        val appComponent: AppComponent = App.appComponent
        appComponent.inject(this)
    }

    @Suppress("UNCHECKED_CAST")
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
                    artistDataRepository
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}