package com.example.sampleapp.repository

import com.example.sampleapp.model.Artist
import com.example.sampleapp.persistence.ArtistDao

class ArtistRepository constructor(private val artistDao: ArtistDao) {

    fun getArtistFavorite(id: Int): Boolean = artistDao.getFavorite(id)

    fun setArtistFavorite(artist: Artist) = artistDao.setFavorite(artist)

    fun insertListArtist(artist: MutableList<Artist>?) = artistDao.insertArtistList(artist)

    fun getListArtist(): MutableList<Artist> = artistDao.getArtistList()

    fun getArtist(id: String): Artist = artistDao.getArtist(id)
}