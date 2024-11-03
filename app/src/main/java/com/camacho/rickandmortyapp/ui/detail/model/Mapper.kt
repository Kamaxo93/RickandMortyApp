package com.camacho.rickandmortyapp.ui.detail.model

import com.camacho.rickandmortyapp.domain.model.CharacterDomain

fun CharacterDomain.toVO() = CharacterDetailVO(
    id = id,
    name = name,
    species = species,
    gender = gender,
    image = image,
    status = status,
    type = type,
    origin = origin,
    location = location,
    episode = episode
)

fun List<CharacterDomain>.toVO(): List<CharacterDetailVO> = map { it.toVO() }