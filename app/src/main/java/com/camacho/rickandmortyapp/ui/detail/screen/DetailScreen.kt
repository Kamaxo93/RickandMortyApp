package com.camacho.rickandmortyapp.ui.detail.screen

import CharacterDetailScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.camacho.rickandmortyapp.R
import com.camacho.rickandmortyapp.ui.detail.viewmodel.DetailViewModel
import com.camacho.rickandmortyapp.ui.home.compose.LoginRickAndMorty


@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    viewModel.initializeDataState(id)
    val state = viewModel.state

    when {
        state.isLoading -> {
            LoginRickAndMorty()
        }

        state.error != null -> {
        }

        state.character != null -> {
            CharacterDetailScreen(character = state.character)
        }
    }
}