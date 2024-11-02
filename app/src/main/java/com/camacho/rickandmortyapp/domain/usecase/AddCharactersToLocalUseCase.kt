package com.camacho.rickandmortyapp.domain.usecase

import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository

interface AddCharactersToLocalUseCase {
    suspend operator fun invoke()
}

class AddCharactersToLocalUseCaseImpl(private val repository: RickAndMortyRepository) :
    AddCharactersToLocalUseCase {
    override suspend fun invoke() {
        repository.addCharactersToLocal()
    }

}