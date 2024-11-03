package com.camacho.rickandmortyapp.ui.home.uistate

import androidx.compose.runtime.Immutable
import com.camacho.rickandmortyapp.ui.home.model.CharacterHomeVO

@Immutable
data class RickAndMortyHomeState(
    val isLoading: Boolean = false,
    val characters: List<CharacterHomeVO>? = null,
    val error: String? = null
)
