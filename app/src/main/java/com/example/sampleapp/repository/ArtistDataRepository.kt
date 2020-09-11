package com.example.sampleapp.repository

import com.example.sampleapp.interfaces.ArtistRepository
import com.example.sampleapp.model.Artist
import com.example.sampleapp.persistence.ArtistDao

class ArtistDataRepository(private val artistDao: ArtistDao) : ArtistRepository {

    override fun insertListArtist(artist: MutableList<Artist>?) {
        artistDao.insertArtistList(artist)
    }

    override fun getListArtist() = artistDao.getArtistList()

    override fun update(favorite_: Boolean, id_: Int) {
        artistDao.updateArtist(favorite_, id_)
    }
}