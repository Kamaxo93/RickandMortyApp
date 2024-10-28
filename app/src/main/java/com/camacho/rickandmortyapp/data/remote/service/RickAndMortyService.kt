package com.camacho.rickandmortyapp.data.remote.service

import com.camacho.rickandmortyapp.data.remote.response.CharacterDTO
import com.camacho.rickandmortyapp.data.remote.response.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("/character")
    suspend fun getAllCharacters(): RickAndMortyResponse

    @GET("/character/{id}")
    suspend fun getDetailCharacter(@Path("id") id: Int): CharacterDTO
}