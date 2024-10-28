package com.camacho.rickandmortyapp.data.remote.datasorce

import com.camacho.rickandmortyapp.data.remote.mapper.toDomain
import com.camacho.rickandmortyapp.data.remote.mapper.toDomains
import com.camacho.rickandmortyapp.data.remote.service.RickAndMortyService
import com.camacho.rickandmortyapp.domain.model.CharacterDomain


interface RickAndMortyRemoteDataSource {

    suspend fun getAllCharacters(): List<CharacterDomain>

    suspend fun getDetailsCharacter(id: Int): CharacterDomain
}

class RickAndMortyRemoteDataSourceImpl(private val service: RickAndMortyService) : RickAndMortyRemoteDataSource {

    override suspend fun getAllCharacters(): List<CharacterDomain> =
        service.getAllCharacters().toDomains()

    override suspend fun getDetailsCharacter(id: Int): CharacterDomain =
        service.getDetailCharacter(id).toDomain()

}