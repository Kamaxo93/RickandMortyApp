package com.camacho.rickandmortyapp.data.local.mapper

import com.camacho.rickandmortyapp.core.common.orZero
import com.camacho.rickandmortyapp.data.local.model.CharacterEntity
import com.camacho.rickandmortyapp.domain.model.CharacterDomain

fun List<CharacterEntity>.toDomains(): List<CharacterDomain> {
    return this.map {
        it.toDomain()
    }
}

fun CharacterEntity?.toDomain() = CharacterDomain(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    status = this?.status.orEmpty(),
    species = this?.species.orEmpty(),
    gender = this?.gender.orEmpty(),
    created = this?.created.orEmpty(),
    episode = this?.episode?.map { it } ?: emptyList(),
    image = this?.image.orEmpty(),
    location = this?.location.orEmpty(),
    origin = this?.origin.orEmpty(),
    type = this?.type.orEmpty(),
    url = this?.url.orEmpty(),
)

fun List<CharacterDomain>.toEntities(): List<CharacterEntity> {
    return this.map {
        it.toEntity()
    }
}

fun CharacterDomain?.toEntity() = CharacterEntity(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    status = this?.status.orEmpty(),
    species = this?.species.orEmpty(),
    gender = this?.gender.orEmpty(),
    created = this?.created.orEmpty(),
    episode = this?.episode?.map { it } ?: emptyList(),
    image = this?.image.orEmpty(),
    location = this?.location.orEmpty(),
    origin = this?.origin.orEmpty(),
    type = this?.type.orEmpty(),
    url = this?.url.orEmpty()
)
