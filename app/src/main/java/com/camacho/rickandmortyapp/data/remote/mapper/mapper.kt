package com.camacho.rickandmortyapp.data.remote.mapper

import com.camacho.rickandmortyapp.core.common.orZero
import com.camacho.rickandmortyapp.data.remote.response.CharacterDTO
import com.camacho.rickandmortyapp.data.remote.response.LocationDTO
import com.camacho.rickandmortyapp.data.remote.response.OriginDTO
import com.camacho.rickandmortyapp.data.remote.response.RickAndMortyResponse
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import com.camacho.rickandmortyapp.domain.model.LocationDomain
import com.camacho.rickandmortyapp.domain.model.OriginDomain

fun RickAndMortyResponse.toDomains(): List<CharacterDomain> {
    return this.results?.map {
        it.toDomain()
    } ?: emptyList()
}

fun CharacterDTO?.toDomain() = CharacterDomain(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    status = this?.status.orEmpty(),
    species = this?.species.orEmpty(),
    gender = this?.gender.orEmpty(),
    created = this?.created.orEmpty(),
    episode = this?.episode?.map { it.orEmpty() } ?: emptyList(),
    image = this?.image.orEmpty(),
    location = this?.location.toDomain(),
    origin = this?.origin.toDomain(),
    type = this?.type.orEmpty(),
    url = this?.url.orEmpty(),
)

fun LocationDTO?.toDomain() = LocationDomain(
    name = this?.name.orEmpty(),
    url = this?.url.orEmpty(),
)

fun OriginDTO?.toDomain() = OriginDomain(
    name = this?.name.orEmpty(),
    url = this?.url.orEmpty(),
)