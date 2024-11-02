package com.camacho.rickandmortyapp.core.di

import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.camacho.rickandmortyapp.domain.usecase.AddCharactersToLocalUseCase
import com.camacho.rickandmortyapp.domain.usecase.AddCharactersToLocalUseCaseImpl
import com.camacho.rickandmortyapp.domain.usecase.GetAllCharactersUseCase
import com.camacho.rickandmortyapp.domain.usecase.GetAllCharactersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetAllCharactersUseCase(repository: RickAndMortyRepository): GetAllCharactersUseCase =
        GetAllCharactersUseCaseImpl(repository)

    @Provides
    fun provideAddCharactersToLocalUseCase(repository: RickAndMortyRepository): AddCharactersToLocalUseCase =
        AddCharactersToLocalUseCaseImpl(repository)

}