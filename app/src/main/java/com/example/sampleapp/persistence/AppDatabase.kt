package com.example.sampleapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampleapp.model.Artist
import com.example.sampleapp.model.FavoriteArtist

@Database(entities = [Artist::class, FavoriteArtist::class], version = 10, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
    abstract fun favoriteDao(): FavoriteDao
}