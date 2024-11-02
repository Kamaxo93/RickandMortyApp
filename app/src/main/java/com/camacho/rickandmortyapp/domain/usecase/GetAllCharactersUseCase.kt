package com.camacho.rickandmortyapp.domain.usecase

import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow


interface GetAllCharactersUseCase {
    suspend operator fun invoke(): Flow<List<CharacterDomain>>
}

class GetAllCharactersUseCaseImpl(private val repository: RickAndMortyRepository): GetAllCharactersUseCase {

    override suspend fun invoke(): Flow<List<CharacterDomain>> =
        repository.getAllCharacters()

}