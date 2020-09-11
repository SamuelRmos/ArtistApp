package com.example.sampleapp.repository

import com.example.sampleapp.base.BaseRepository
import com.example.sampleapp.interfaces.ApiRepository
import com.example.sampleapp.model.Artist
import com.example.sampleapp.network.ArtistApi

class ArtistApiRepository constructor(
        private val artistApi: ArtistApi,
        private val artistDataRepository: ArtistDataRepository
) : BaseRepository(), ApiRepository {

    private val artistList = artistDataRepository.getListArtist()

    override suspend fun getArtist() = when (artistList.size) {
        0 -> dataFetchLogic()
        else -> artistList
    }

    override suspend fun dataFetchLogic(): MutableList<Artist>? {
        val artistResponse = safeApiCall(
                call = { artistApi.getArtistDayAsync().await() },
                errorMessage = "Error Fetching Data from API"
        )
        val dataReceived = artistResponse?.toMutableList()
        artistDataRepository.insertListArtist(dataReceived)
        return dataReceived
    }
}