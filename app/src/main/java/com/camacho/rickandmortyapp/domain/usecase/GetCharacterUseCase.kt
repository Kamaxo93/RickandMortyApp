package com.camacho.rickandmortyapp.domain.usecase

import com.camacho.rickandmortyapp.core.async.AsyncResult
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow

interface GetCharacterUseCase {
    suspend operator fun invoke(id: String): Flow<AsyncResult<CharacterDomain>>

}

class GetCharacterUseCaseImpl(private val repository: RickAndMortyRepository) :
    GetCharacterUseCase {

    override suspend fun invoke(id: String): Flow<AsyncResult<CharacterDomain>> {
        return repository.getCharacter(id)
    }
}