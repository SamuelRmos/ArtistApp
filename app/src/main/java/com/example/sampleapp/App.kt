package com.example.sampleapp

import android.app.Application
import android.content.Context
import com.example.sampleapp.di.*
import com.example.sampleapp.util.Constants

class App : Application() {

    companion object {
        lateinit var ctx: Context
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        appComponent = initDaggerComponent()
    }

    private fun initDaggerComponent() = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .apiModule(ApiModule(Constants.baseURL))
        .userRepositoryModule(UserRepositoryModule())
        .persistenceDataModule(PersistenceDataModule())
        .repositoryModule(RepositoryModule())
        .build()
}