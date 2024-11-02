package com.camacho.rickandmortyapp.ui.home.uistate

import androidx.compose.runtime.Immutable
import com.camacho.rickandmortyapp.domain.model.CharacterDomain

@Immutable
data class RickAndMortyHomeState(
    val isLoading: Boolean = false,
    val characters: List<CharacterDomain>? = null,
    val error: String? = null
)
