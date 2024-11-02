package com.camacho.rickandmortyapp.core.di

import android.content.Context
import androidx.room.Room
import com.camacho.rickandmortyapp.core.database.RickAndMortyDataBase
import com.camacho.rickandmortyapp.data.constan.Constant.NAME_DATA_BASE
import com.camacho.rickandmortyapp.data.local.dao.RickAndMortyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideCharacterDao(rickAndMortyDataBase: RickAndMortyDataBase): RickAndMortyDao {
        return rickAndMortyDataBase.rickAndMortyDao()
    }

    @Provides
    fun provideDragonBallDataBase(@ApplicationContext appContext: Context): RickAndMortyDataBase {
        return Room.databaseBuilder(appContext, RickAndMortyDataBase::class.java, NAME_DATA_BASE)
            .build()
    }
}