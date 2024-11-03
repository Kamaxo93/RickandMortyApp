package com.camacho.rickandmortyapp.domain.usecase

import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow

interface GetCharacterUseCase {
    suspend operator fun invoke(id: String): Flow<CharacterDomain>

}

class GetCharacterUseCaseImpl(private val repository: RickAndMortyRepository) :
    GetCharacterUseCase {

    override suspend fun invoke(id: String): Flow<CharacterDomain> {
        return repository.getCharacter(id)
    }
}