package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistDataRepository

class FavoriteViewModel(
        private val artistDataRepository: ArtistDataRepository
) : ViewModel() {
    val favoriteLiveData = artistFavorites()

    private fun artistFavorites(): MutableLiveData<MutableList<Artist>> {
        val favoriteList = MutableLiveData<MutableList<Artist>>()
        val favorite = mutableListOf<Artist>()

        for (data in artistDataRepository.getListArtist()) {
            if (data.favorite == true)
                favorite.add(data)
        }

        favoriteList.postValue(favorite)
        return favoriteList

    }
}