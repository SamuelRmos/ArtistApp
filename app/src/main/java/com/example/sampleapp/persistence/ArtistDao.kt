package com.example.sampleapp.persistence

import androidx.room.*
import com.example.sampleapp.model.Artist

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtistList(artist: MutableList<Artist>?)

    @Query("SELECT * FROM Artist WHERE id = :id_")
    fun getArtist(id_: String): Artist

    @Query("UPDATE Artist SET favorite = :favorite_ WHERE id = :id_")
    fun updateArtist(favorite_: Boolean, id_: Int)

    @Query("SELECT * FROM Artist")
    fun getArtistList(): MutableList<Artist>

    @Query("SELECT favorite FROM Artist WHERE id = :id_")
    fun getFavorite(id_: Int): Boolean

}