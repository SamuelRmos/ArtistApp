package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.App
import com.example.sampleapp.di.AppComponent
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistApiRepository
import com.example.sampleapp.repository.ArtistDataRepository
import com.example.sampleapp.repository.UserRepository
import com.example.sampleapp.util.LiveDataResult
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

    private val favorite = mutableListOf<Artist>()
    private val repositoriesLiveData = MutableLiveData<LiveDataResult<MutableList<Artist>>>()

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
                    repositoriesLiveData,
                    Dispatchers.Main,
                    Dispatchers.IO
            ) as T

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(
                    artistDataRepository,
                    repositoriesLiveData,
                    favorite
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}