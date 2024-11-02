package com.camacho.rickandmortyapp.data.local.datasource

import com.camacho.rickandmortyapp.data.local.dao.RickAndMortyDao
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface RickAndMortyLocalDataSource {
    fun getAllCharacters(): Flow<List<CharacterEntity>>
    fun getCharacter(id: String): Flow<CharacterEntity>
    suspend fun addCharacter(items: List<CharacterEntity>)
}

class RickAndMortyLocalDataSourceImpl(private val dao: RickAndMortyDao) : RickAndMortyLocalDataSource {

    override fun getAllCharacters(): Flow<List<CharacterEntity>> {
        return dao.getAllCharacters()
    }

    override fun getCharacter(id: String): Flow<CharacterEntity> {
       return dao.getCharacter(id)
    }

    override suspend fun addCharacter(items: List<CharacterEntity>) {
        dao.addCharacter(items)
    }

}