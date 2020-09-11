package com.example.sampleapp.interfaces

import com.example.sampleapp.model.Artist

interface ArtistRepository {
    fun insertListArtist(artist: MutableList<Artist>?)
    fun getListArtist(): MutableList<Artist>
    fun update(favorite_: Boolean, id_: Int)
}