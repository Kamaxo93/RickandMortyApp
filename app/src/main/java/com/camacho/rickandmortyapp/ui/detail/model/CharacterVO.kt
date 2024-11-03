package com.camacho.rickandmortyapp.ui.detail.model

data class CharacterDetailVO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episode: List<String>
)
