package com.camacho.rickandmortyapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<CharacterDTO?>?
)