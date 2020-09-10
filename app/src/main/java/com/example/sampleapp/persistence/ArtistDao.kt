package com.example.sampleapp.persistence

import androidx.room.*
import com.example.sampleapp.model.Artist

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtistList(artist: MutableList<Artist>?)

    @Query("SELECT * FROM Artist WHERE id = :id_")
    fun getArtist(id_: String): Artist

    @Query("SELECT * FROM Artist")
    fun getArtistList(): MutableList<Artist>

    @Query("SELECT favorite FROM Artist WHERE id = :id_")
    fun getFavorite(id_: Int): Boolean

    @Update
    fun setFavorite(artist: Artist)
}