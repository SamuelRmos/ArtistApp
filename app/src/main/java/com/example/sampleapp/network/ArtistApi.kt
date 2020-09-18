package com.example.sampleapp.network

import com.example.sampleapp.model.Artist
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ArtistApi {
    @GET("DisneyPosters.json")
    fun getArtistDayAsync(): Deferred<Response<List<Artist>>>
}