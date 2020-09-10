package com.example.sampleapp.di

import androidx.room.Room
import com.example.sampleapp.App
import com.example.sampleapp.R
import com.example.sampleapp.persistence.AppDatabase
import com.example.sampleapp.persistence.ArtistDao
import com.example.sampleapp.persistence.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceDataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: App): AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        application.getString(R.string.artist_db)
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideArtistDao(appDatabase: AppDatabase): ArtistDao = appDatabase.artistDao()

    @Singleton
    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao = appDatabase.favoriteDao()
}