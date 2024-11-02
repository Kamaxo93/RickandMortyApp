package com.camacho.rickandmortyapp.domain.usecase

import com.camacho.rickandmortyapp.core.async.AsyncResult
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow

interface AddCharactersToLocalUseCase {
    suspend operator fun invoke(): Flow<AsyncResult<Unit>>
}

class AddCharactersToLocalUseCaseImpl(private val repository: RickAndMortyRepository) :
    AddCharactersToLocalUseCase {
    override suspend fun invoke(): Flow<AsyncResult<Unit>> {
        return repository.addCharactersToLocal()
    }

}