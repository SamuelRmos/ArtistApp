package com.example.sampleapp.interfaces

import com.example.sampleapp.model.Artist

interface ApiRepository {
    suspend fun getArtist(): MutableList<Artist>?
    suspend fun dataFetchLogic(): MutableList<Artist>?
}