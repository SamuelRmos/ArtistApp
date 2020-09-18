package com.example.sampleapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistApiRepository
import com.example.sampleapp.util.LiveDataResult
import kotlinx.coroutines.*

class MainViewModel(
        private val artistApiRepository: ArtistApiRepository,
        private val repositoriesLiveData: MutableLiveData<LiveDataResult<MutableList<Artist>>>,
        mainDispatcher: CoroutineDispatcher,
        ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)
    private val mIoScope = CoroutineScope(ioDispatcher + job)

    fun fetchArtist(): MutableLiveData<LiveDataResult<MutableList<Artist>>> {

        mUiScope.launch {
            repositoriesLiveData.value = LiveDataResult.loading()
            try {
                val data = mIoScope.async {
                    return@async artistApiRepository.getArtist()
                }.await()

                repositoriesLiveData.value = LiveDataResult.success(data!!)
            } catch (e: Exception) {
                repositoriesLiveData.value = LiveDataResult.error(e)
            }
        }
        return repositoriesLiveData
    }

}