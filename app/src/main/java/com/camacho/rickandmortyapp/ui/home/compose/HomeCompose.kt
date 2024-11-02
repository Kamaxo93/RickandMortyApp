package com.camacho.rickandmortyapp.ui.home.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
import kotlinx.coroutines.launch

@Composable
fun RickAndMortyCharacterList(characters: List<CharacterDomain>?, onItemClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        ToolbarRickAndMorty()
        CharacterList(characters = characters, isSearchCharacters = false) {
            onItemClick(it)
        }
    }

}

@Composable
fun CharacterList(
    characters: List<CharacterDomain>?,
    isSearchCharacters: Boolean,
    onClickElement: (String) -> Unit
) {
    val state = rememberLazyListState(0)
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = state) {
        if (isSearchCharacters) {
            coroutineScope.launch {
                state.scrollToItem(0)
            }
        }
        items(characters ?: listOf(), key = { it.id }) {
            ItemList(character = it) { item ->
                onClickElement(item)
            }
        }
    }
}

@Composable
fun ItemList(character: CharacterDomain, onClickElement: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .clip(RoundedCornerShape(24))
            .border(
                2.dp,
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(0, 24, 0, 24)
            )
            .fillMaxWidth()
            .height(250.dp)
            .clickable {
                onClickElement(character.id.toString())
            }, contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "character image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = character.name, color = Color.White, fontSize = 18.sp)
        }
    }
}