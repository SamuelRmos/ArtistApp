package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.FavoriteArtist
import com.example.sampleapp.persistence.FavoriteDao
import com.example.sampleapp.repository.FavoriteRepository

class FavoriteViewModel(private val databaseRepository: FavoriteRepository) : ViewModel() {
    val favoriteLiveData = artistFavorites()

    private fun artistFavorites(): MutableLiveData<MutableList<FavoriteArtist>> {
        val favoriteList = MutableLiveData<MutableList<FavoriteArtist>>()
        favoriteList.postValue(databaseRepository.getFavoriteList())
        return favoriteList
    }
}