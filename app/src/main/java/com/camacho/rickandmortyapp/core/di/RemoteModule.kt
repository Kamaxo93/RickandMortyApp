package com.camacho.rickandmortyapp.core.di

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
class RemoteModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: RickAndMortyService): RickAndMortyRemoteDataSource {
        return RickAndMortyRemoteDataSourceImpl(service)
    }
}