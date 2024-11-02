package com.camacho.rickandmortyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RickAndMortyDao {
    @Query("SELECT * FROM CharacterEntity")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * from CharacterEntity WHERE id = :id")
    fun getCharacter(id: String): Flow<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(items: List<CharacterEntity>)
}