package com.camacho.rickandmortyapp.data.remote.datasorce

import com.camacho.rickandmortyapp.core.common.orZero
import com.camacho.rickandmortyapp.data.constan.Constant.MAX_NUMBER_CHARACTER
import com.camacho.rickandmortyapp.data.constan.Constant.MAX_NUMBER_PAGE
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity
import com.camacho.rickandmortyapp.data.remote.mapper.toEntities
import com.camacho.rickandmortyapp.data.remote.service.RickAndMortyService


interface RickAndMortyRemoteDataSource {
    suspend fun getAllCharacters(page: Int?): List<CharacterEntity>
}

class RickAndMortyRemoteDataSourceImpl(private val service: RickAndMortyService) :
    RickAndMortyRemoteDataSource {

    override suspend fun getAllCharacters(page: Int?): List<CharacterEntity> {
        val response = service.getAllCharacters(page.orZero())
        MAX_NUMBER_PAGE = response.info?.pages.orZero()
        MAX_NUMBER_CHARACTER = response.info?.count.orZero()
        return response.toEntities()
    }

}