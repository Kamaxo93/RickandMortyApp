package com.camacho.rickandmortyapp.core.di

import android.content.SharedPreferences
import com.camacho.rickandmortyapp.data.local.dao.RickAndMortyDao
import com.camacho.rickandmortyapp.data.local.datasource.RickAndMortyLocalDataSource
import com.camacho.rickandmortyapp.data.local.datasource.RickAndMortyLocalDataSourceImpl
import com.camacho.rickandmortyapp.data.remote.datasorce.RickAndMortyRemoteDataSource
import com.camacho.rickandmortyapp.data.remote.datasorce.RickAndMortyRemoteDataSourceImpl
import com.camacho.rickandmortyapp.data.remote.service.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        service: RickAndMortyService,
        @MySharedPrefs sharedPreferences: SharedPreferences
    ): RickAndMortyRemoteDataSource {
        return RickAndMortyRemoteDataSourceImpl(service, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: RickAndMortyDao): RickAndMortyLocalDataSource {
        return RickAndMortyLocalDataSourceImpl(dao)
    }

}