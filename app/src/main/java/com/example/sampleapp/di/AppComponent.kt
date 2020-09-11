package com.example.sampleapp.di

import com.example.sampleapp.view.fragment.MainFragment
import com.example.sampleapp.viewmodel.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, UserRepositoryModule::class,
        ApiModule::class, PersistenceDataModule::class, RepositoryModule::class]
)

interface AppComponent {
    fun inject(viewModelFactory: ViewModelFactory)
    fun inject(mainFragment: MainFragment)
}