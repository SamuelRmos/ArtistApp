package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistDataRepository

class FavoriteViewModel(
        private val artistDataRepository: ArtistDataRepository,
        favoriteList: MutableLiveData<MutableList<Artist>>,
        favorite: MutableList<Artist>
) : ViewModel() {

    val favoriteLiveData = artistFavorites(favoriteList, favorite)

    private fun artistFavorites(
            favoriteList: MutableLiveData<MutableList<Artist>>,
            favorite: MutableList<Artist>
    ): MutableLiveData<MutableList<Artist>> {

        for (data in artistDataRepository.getListArtist()) {
            if (data.favorite == true)
                favorite.add(data)
        }

        favoriteList.postValue(favorite)
        return favoriteList
    }

}