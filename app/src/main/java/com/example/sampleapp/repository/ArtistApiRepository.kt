package com.example.sampleapp.repository

import com.example.sampleapp.base.BaseRepository
import com.example.sampleapp.model.Artist
import com.example.sampleapp.network.ArtistApi

class ArtistApiRepository constructor(
    private val artistApi: ArtistApi,
    private val artistRepository: ArtistRepository
) : BaseRepository() {

    private val artistList = artistRepository.getListArtist()

    suspend fun getArtist() = when (artistList.size) {
        0 -> dataFetchLogic()
        else -> artistList
    }

    private suspend fun dataFetchLogic(): MutableList<Artist>? {

        val artistResponse = safeApiCall(
            call = { artistApi.getArtistDayAsync().await() },
            errorMessage = "Error Fetching Data from API"
        )
        val dataReceived = artistResponse?.toMutableList()
        artistRepository.insertListArtist(dataReceived)

        return dataReceived
    }
}