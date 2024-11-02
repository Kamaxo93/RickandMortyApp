package com.camacho.rickandmortyapp.data.remote.service

import com.camacho.rickandmortyapp.data.remote.response.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("/api/character/")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1
    ): RickAndMortyResponse
}