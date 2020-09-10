package com.example.sampleapp.repository

import com.example.sampleapp.App
import com.example.sampleapp.di.AppComponent
import com.example.sampleapp.model.FavoriteArtist
import com.example.sampleapp.persistence.FavoriteDao
import javax.inject.Inject

class FavoriteRepository {

    @Inject
    lateinit var favoriteDao: FavoriteDao

    init {
        val appComponent: AppComponent = App.appComponent
        appComponent.inject(this)
    }

    fun insertFavorite(favoriteList: MutableList<FavoriteArtist>?) =
        favoriteDao.insertFavoriteArtist(favoriteList)

    fun removeFavorite(id : Int) = favoriteDao.removeFavoriteArtist(id)

    fun getFavoriteList() = favoriteDao.getFavoriteList()
}