package com.camacho.rickandmortyapp.data.repository

import android.content.SharedPreferences
import com.camacho.rickandmortyapp.core.async.AsyncResult
import com.camacho.rickandmortyapp.core.async.RepositoryErrorManager
import com.camacho.rickandmortyapp.core.di.MySharedPrefs
import com.camacho.rickandmortyapp.data.constan.Constant.MAX_NUMBER_PAGE
import com.camacho.rickandmortyapp.data.local.datasource.RickAndMortyLocalDataSource
import com.camacho.rickandmortyapp.data.local.mapper.toDomains
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity
import com.camacho.rickandmortyapp.data.remote.datasorce.RickAndMortyRemoteDataSource
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import com.camacho.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RickAndMortyRepositoryImpl(
    private val remoteDataSource: RickAndMortyRemoteDataSource,
    private val localDataSource: RickAndMortyLocalDataSource,
    @MySharedPrefs private val sharedPreferences: SharedPreferences
) :
    RickAndMortyRepository {
    val list = mutableListOf<CharacterEntity>()

    override suspend fun getAllCharacters(): Flow<List<CharacterDomain>> {
        return localDataSource.getAllCharacters().map {
            it.toDomains()
        }
    }

    override suspend fun addCharactersToLocal(): Flow<AsyncResult<Unit>> {
        return flow {
            getCharactersFromRemote(null).collect { response ->
                if (response is AsyncResult.Success) {
                    list.addAll(response.data)
                    for (i in 2..sharedPreferences.getInt(MAX_NUMBER_PAGE, 0)) {
                        getCharactersFromRemote(i).collect {
                            if (it is AsyncResult.Success) {
                                list.addAll(it.data)
                            }
                        }
                    }
                } else if (response is AsyncResult.Error) {
                    emit(AsyncResult.Error(response.error))
                }
            }
            localDataSource.addCharacter(list)
        }
    }

    private suspend fun getCharactersFromRemote(page: Int?):
            Flow<AsyncResult<List<CharacterEntity>>> {
        return RepositoryErrorManager.wrap {
            remoteDataSource.getAllCharacters(page)
        }
    }

}