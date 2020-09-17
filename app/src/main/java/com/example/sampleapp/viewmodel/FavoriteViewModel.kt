package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistDataRepository

class FavoriteViewModel(
        private val artistDataRepository: ArtistDataRepository,
        private val favoriteList: MutableLiveData<MutableList<Artist>>,
        private val favorite: MutableList<Artist>
) : ViewModel() {

    fun artistFavorites(): MutableLiveData<MutableList<Artist>> {

        for (data in artistDataRepository.getListArtist()) {
            if (data.favorite == true)
                favorite.add(data)
        }

        favoriteList.postValue(favorite)
        return favoriteList
    }

}