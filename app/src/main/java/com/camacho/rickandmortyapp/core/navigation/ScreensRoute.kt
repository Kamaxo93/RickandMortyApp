package com.camacho.rickandmortyapp.core.navigation

import kotlinx.serialization.Serializable


@Serializable
object Home

@Serializable
data class Detail(val id: String)