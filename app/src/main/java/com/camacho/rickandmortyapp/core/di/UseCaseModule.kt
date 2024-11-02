package com.camacho.rickandmortyapp.core.di

import android.content.SharedPreferences
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.camacho.rickandmortyapp.domain.usecase.AddCharactersToLocalUseCase
import com.camacho.rickandmortyapp.domain.usecase.AddCharactersToLocalUseCaseImpl
import com.camacho.rickandmortyapp.domain.usecase.GetAllCharactersUseCase
import com.camacho.rickandmortyapp.domain.usecase.GetAllCharactersUseCaseImpl
import com.camacho.rickandmortyapp.domain.usecase.GetTotalsCharacters
import com.camacho.rickandmortyapp.domain.usecase.GetTotalsCharactersImpl
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

    @Provides
    fun provideGetTotalsCharactersUseCase(@MySharedPrefs shared: SharedPreferences): GetTotalsCharacters =
        GetTotalsCharactersImpl(shared)
}