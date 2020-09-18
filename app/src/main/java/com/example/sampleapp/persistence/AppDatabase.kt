package com.example.sampleapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampleapp.model.Artist

@Database(entities = [Artist::class], version = 11, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}