package com.camacho.rickandmortyapp.core.di

import com.camacho.rickandmortyapp.data.remote.datasorce.RickAndMortyRemoteDataSource
import com.camacho.rickandmortyapp.data.repository.RickAndMortyRepositoryImpl
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRickAndMortyRepository(dataSource: RickAndMortyRemoteDataSource): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(dataSource)
    }

}