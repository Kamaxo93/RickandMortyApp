package com.camacho.rickandmortyapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.camacho.rickandmortyapp.data.local.converters.Converters
import com.camacho.rickandmortyapp.data.local.dao.RickAndMortyDao
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class RickAndMortyDataBase: RoomDatabase() {
    abstract fun rickAndMortyDao(): RickAndMortyDao

}