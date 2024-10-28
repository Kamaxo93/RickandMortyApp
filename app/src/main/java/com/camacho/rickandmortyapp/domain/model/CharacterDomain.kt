package com.camacho.rickandmortyapp.domain.model


data class CharacterDomain(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDomain,
    val name: String,
    val origin: OriginDomain,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)