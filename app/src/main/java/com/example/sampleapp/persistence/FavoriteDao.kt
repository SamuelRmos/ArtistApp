package com.example.sampleapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sampleapp.model.FavoriteArtist

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteArtist(artist: MutableList<FavoriteArtist>?)

    @Query("DELETE FROM Favorite WHERE id =:id_")
    fun removeFavoriteArtist(id_: Int)

     @Query("SELECT * FROM Favorite")
    fun getFavoriteList(): MutableList<FavoriteArtist>
}