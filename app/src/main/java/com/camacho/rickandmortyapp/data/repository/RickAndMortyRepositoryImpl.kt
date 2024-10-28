package com.camacho.rickandmortyapp.data.repository

import com.camacho.loompasapp.core.async.AsyncError
import com.camacho.loompasapp.core.async.AsyncResult
import com.camacho.loompasapp.core.async.RepositoryErrorManager
import com.camacho.rickandmortyapp.data.remote.datasorce.RickAndMortyRemoteDataSource
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RickAndMortyRepositoryImpl(private val dataSource: RickAndMortyRemoteDataSource) :
    RickAndMortyRepository {
    private val characters = mutableListOf<CharacterDomain>()

    override suspend fun getAllCharacters(): Flow<AsyncResult<List<CharacterDomain>>> {
        return if (characters.isNotEmpty()) {
            return flow { emit(AsyncResult.Success(characters)) }

        } else {
            RepositoryErrorManager.wrap {
                characters.addAll(dataSource.getAllCharacters())
                characters
            }
        }
    }


    override fun getDetailCharacter(id: Int): Flow<AsyncResult<CharacterDomain>> = flow {
        emit(AsyncResult.Loading())
        if (characters.isEmpty()) {
            emit(AsyncResult.Error(AsyncError.EmptyResponseError))

        } else {
            emit(AsyncResult.Success(characters.first { it.id == id }))
        }
    }
}