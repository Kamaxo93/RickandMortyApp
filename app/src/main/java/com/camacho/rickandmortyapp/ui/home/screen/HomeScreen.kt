package com.camacho.rickandmortyapp.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.camacho.rickandmortyapp.R
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
            CircularProgressIndicator()
        }
        state.error != null -> {

        }
        state.characters.isNullOrEmpty().not() -> {

        }

        else -> {

        }
    }


}