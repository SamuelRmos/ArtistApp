package com.example.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistApiRepository
import kotlinx.coroutines.*

class MainViewModel(
        private val artistApiRepository: ArtistApiRepository,
        private val artistList: MutableLiveData<MutableList<Artist>>,
        mainDispatcher: CoroutineDispatcher,
        ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)
    private val mIoScope = CoroutineScope(ioDispatcher + job)

    var artistLiveData = fetchArtist()

    private fun fetchArtist(): LiveData<MutableList<Artist>> {

        mUiScope.launch {
            val data = mIoScope.async {
                return@async artistApiRepository.getArtist()
            }.await()

            artistList.postValue(data)
        }
        return artistList
    }

}