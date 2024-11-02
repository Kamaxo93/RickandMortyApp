package com.camacho.rickandmortyapp.data.remote.datasorce

import android.content.SharedPreferences
import com.camacho.rickandmortyapp.core.common.orZero
import com.camacho.rickandmortyapp.core.di.MySharedPrefs
import com.camacho.rickandmortyapp.data.constan.Constant.MAX_NUMBER_CHARACTER
import com.camacho.rickandmortyapp.data.constan.Constant.MAX_NUMBER_PAGE
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity
import com.camacho.rickandmortyapp.data.remote.mapper.toEntities
import com.camacho.rickandmortyapp.data.remote.service.RickAndMortyService


interface RickAndMortyRemoteDataSource {
    suspend fun getAllCharacters(page: Int?): List<CharacterEntity>
}

class RickAndMortyRemoteDataSourceImpl(
    private val service: RickAndMortyService,
    @MySharedPrefs private val sharedPreferences: SharedPreferences
) :
    RickAndMortyRemoteDataSource {

    override suspend fun getAllCharacters(page: Int?): List<CharacterEntity> {
        val response = service.getAllCharacters(page.orZero())
        sharedPreferences.edit().putInt(MAX_NUMBER_PAGE, response.info?.pages.orZero()).apply()
        sharedPreferences.edit().putInt(MAX_NUMBER_CHARACTER, response.info?.count.orZero()).apply()
        return response.toEntities()
    }

}