package com.camacho.rickandmortyapp.data.remote.mapper

import com.camacho.rickandmortyapp.core.common.orZero
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity
import com.camacho.rickandmortyapp.data.remote.response.CharacterDTO
import com.camacho.rickandmortyapp.data.remote.response.RickAndMortyResponse

fun RickAndMortyResponse.toEntities(): List<CharacterEntity> {
    return this.results?.map {
        it.toEntity()
    } ?: emptyList()
}

fun CharacterDTO?.toEntity() = CharacterEntity(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    status = this?.status.orEmpty(),
    species = this?.species.orEmpty(),
    gender = this?.gender.orEmpty(),
    created = this?.created.orEmpty(),
    episode = this?.episode?.map { it.orEmpty() } ?: emptyList(),
    image = this?.image.orEmpty(),
    location = this?.location?.name.orEmpty(),
    origin = this?.origin?.name.orEmpty(),
    type = this?.type.orEmpty(),
    url = this?.url.orEmpty()
)