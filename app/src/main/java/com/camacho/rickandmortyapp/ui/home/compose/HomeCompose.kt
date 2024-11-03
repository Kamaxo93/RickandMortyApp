package com.camacho.rickandmortyapp.ui.home.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.camacho.rickandmortyapp.ui.home.model.CharacterHomeVO
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyCharacterList(
    characters: List<CharacterHomeVO>?,
    genders: List<String>,
    species: List<String>,
    onSelectFilter: (String, String) -> Unit,
    onItemClick: (String) -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("") }
    var selectedSpecies by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        ToolbarRickAndMorty {
            showBottomSheet = true
        }
        CharacterList(characters = characters, isSearchCharacters = false) {
            onItemClick(it)
        }
        if (showBottomSheet) {
            MyBottomSheet(sheetState = sheetState, onDismissRequest = {
                showBottomSheet = false
            }) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Filtros")
                    Spacer(modifier = Modifier.size(16.dp))
                    Row {
                        FilterItems("Gender", genders) {
                            selectedGender = it
                        }
                        Spacer(modifier = Modifier.size(100.dp))
                        FilterItems("Species", species) {
                            selectedSpecies = it
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(onClick = {
                        showBottomSheet = false
                        onSelectFilter(selectedGender, selectedSpecies)
                        selectedGender = ""
                        selectedSpecies = ""

                    }) {
                        Text("Aceptar")
                    }
                }

            }

            LaunchedEffect(showBottomSheet) {
                if (showBottomSheet) {
                    sheetState.show()

                } else {
                    sheetState.hide()
                }
            }

        }
    }
}

@Composable
fun CharacterList(
    characters: List<CharacterHomeVO>?,
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
fun ItemList(character: CharacterHomeVO, onClickElement: (String) -> Unit) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState
    ) {
        content()
    }
}

@Composable
fun FilterItems(title: String, filters: List<String>, onSelectFilter: (String) -> Unit) {
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    Column {
        Text(title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        LazyColumn(Modifier.padding()) {
            itemsIndexed(filters) { index, item ->
                val isSelected = selectedIndex.value == index

                Box(
                    modifier = Modifier
                        .clickable {
                            selectedIndex.value = index
                            onSelectFilter(item)
                        }
                        .background(if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent)
                        .padding(8.dp)
                ) {
                    Text(text = item)
                }
            }
        }
    }
}