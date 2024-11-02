package com.camacho.rickandmortyapp.data.repository

import com.camacho.rickandmortyapp.core.async.AsyncResult
import com.camacho.rickandmortyapp.core.async.RepositoryErrorManager
import com.camacho.rickandmortyapp.data.constan.Constant.MAX_NUMBER_PAGE
import com.camacho.rickandmortyapp.data.local.datasource.RickAndMortyLocalDataSource
import com.camacho.rickandmortyapp.data.local.mapper.toDomains
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity
import com.camacho.rickandmortyapp.data.remote.datasorce.RickAndMortyRemoteDataSource
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RickAndMortyRepositoryImpl(
    private val remoteDataSource: RickAndMortyRemoteDataSource,
    private val localDataSource: RickAndMortyLocalDataSource
) :
    RickAndMortyRepository {
    val list = mutableListOf<CharacterEntity>()

    override suspend fun getAllCharacters(): Flow<List<CharacterDomain>> {
        return localDataSource.getAllCharacters().map {
            it.toDomains()
        }
    }

    override suspend fun addCharactersToLocal() {
        getCharactersFromRemote(null).collect {
            if (it is AsyncResult.Success) {
               list.addAll(it.data)
                for (i in 2..MAX_NUMBER_PAGE) {
                    getCharactersFromRemote(i).collect {
                        if (it is AsyncResult.Success) {
                            list.addAll(it.data)
                        }
                    }
                }
            }
        }
        localDataSource.addCharacter(list)
    }

    private suspend fun getCharactersFromRemote(page: Int?):
            Flow<AsyncResult<List<CharacterEntity>>> {
        return RepositoryErrorManager.wrap {
            remoteDataSource.getAllCharacters(page)
        }
    }

}