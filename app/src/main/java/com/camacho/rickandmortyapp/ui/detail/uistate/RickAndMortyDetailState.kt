package com.camacho.rickandmortyapp.ui.detail.uistate

import androidx.compose.runtime.Immutable
import com.camacho.rickandmortyapp.ui.detail.model.CharacterDetailVO

@Immutable
data class RickAndMortyDetailState(
    val isLoading: Boolean = false,
    val character: CharacterDetailVO? = null,
    val error: String? = null
)
