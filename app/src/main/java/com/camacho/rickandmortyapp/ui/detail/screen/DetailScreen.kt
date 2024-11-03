package com.camacho.rickandmortyapp.ui.detail.screen

import CharacterDetailScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.camacho.rickandmortyapp.ui.detail.viewmodel.DetailViewModel


@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    viewModel.initializeDataState(id)
    val state = viewModel.state
    when {
        state.error != null -> {
        }

        state.character != null -> {
            CharacterDetailScreen(character = state.character)
        }
    }
}