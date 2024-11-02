package com.camacho.rickandmortyapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
)
