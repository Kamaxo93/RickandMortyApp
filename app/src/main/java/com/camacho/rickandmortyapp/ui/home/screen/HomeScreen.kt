package com.camacho.rickandmortyapp.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.camacho.rickandmortyapp.R
import com.camacho.rickandmortyapp.ui.home.compose.CharactersContainerError
import com.camacho.rickandmortyapp.ui.home.compose.LoginRickAndMorty
import com.camacho.rickandmortyapp.ui.home.compose.RickAndMortyCharacterList
import com.camacho.rickandmortyapp.ui.home.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit,
) {
    viewModel.initializeDataState()
    val state = viewModel.state

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    when {
        state.isLoading -> {
            LoginRickAndMorty()
        }

        state.error != null -> {
            CharactersContainerError(state.error) {
                viewModel.reload()
            }
        }

        state.characters.isNullOrEmpty().not() -> {
            RickAndMortyCharacterList(
                characters = state.characters,
                genders = viewModel.getGenders(),
                species = viewModel.getSpecies(),
                onSelectFilter = { gender, species ->
                    viewModel.filterCharacters(gender, species)
                },
                onItemClick = {
                    onItemClick(it)
                }
            )

        }
    }
}