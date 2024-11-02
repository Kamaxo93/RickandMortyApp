package com.camacho.rickandmortyapp.domain.repository

import com.camacho.rickandmortyapp.core.async.AsyncResult
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {

    suspend fun getAllCharacters(): Flow<List<CharacterDomain>>

    suspend fun addCharactersToLocal(): Flow<AsyncResult<Unit>>
}