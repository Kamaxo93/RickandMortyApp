package com.camacho.rickandmortyapp.domain.repository

import com.camacho.loompasapp.core.async.AsyncResult
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {

    suspend fun getAllCharacters(): Flow<AsyncResult<List<CharacterDomain>>>

    fun getDetailCharacter(id: Int): Flow<AsyncResult<CharacterDomain>>

}