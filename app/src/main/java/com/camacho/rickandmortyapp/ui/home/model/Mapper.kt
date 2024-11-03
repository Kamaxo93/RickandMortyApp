package com.camacho.rickandmortyapp.ui.home.model

import com.camacho.rickandmortyapp.domain.model.CharacterDomain

fun CharacterDomain.toVO() = CharacterHomeVO(
    id = id,
    name = name,
    species = species,
    gender = gender,
    image = image,
)

fun List<CharacterDomain>.toVO(): List<CharacterHomeVO> = map { it.toVO() }