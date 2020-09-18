package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistDataRepository
import com.example.sampleapp.util.LiveDataResult

class FavoriteViewModel(
        private val artistDataRepository: ArtistDataRepository,
        private val repositoriesLiveData: MutableLiveData<LiveDataResult<MutableList<Artist>>>,
        private val favorite: MutableList<Artist>
) : ViewModel() {

    fun artistFavorites(): MutableLiveData<LiveDataResult<MutableList<Artist>>> {
        repositoriesLiveData.value = LiveDataResult.loading()
        try {

            for (data in artistDataRepository.getListArtist()) {
                if (data.favorite == true)
                    favorite.add(data)
            }

            repositoriesLiveData.value = LiveDataResult.success(favorite)
        } catch (e: Exception) {
            repositoriesLiveData.value = LiveDataResult.error(e)
        }

        return repositoriesLiveData
    }

}