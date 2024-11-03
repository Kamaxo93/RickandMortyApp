import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.camacho.rickandmortyapp.R
import com.camacho.rickandmortyapp.ui.detail.model.CharacterDetailVO

@Composable
fun CharacterDetailScreen(character: CharacterDetailVO) {
    DetailScreen(character)
}

@Composable
fun DetailScreen(character: CharacterDetailVO) {
    Column {
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
        ) {
            item {
                CharacterHeader(character)
            }
            item {
                CharacterInfoSection(stringResource(R.string.detail_text_status), character.status)
            }
            item {
                CharacterInfoSection(stringResource(R.string.detail_text_species), character.species)
            }
            item {
                CharacterInfoSection(stringResource(R.string.detail_text_gender), character.gender)
            }
            item {
                CharacterInfoSection(stringResource(R.string.detail_text_origin), character.origin)
            }
            item {
                CharacterInfoSection(stringResource(R.string.detail_text_location), character.location)
            }
            item {
                CharacterInfoSection(stringResource(R.string.detail_text_type), character.type)
            }
            item {
                CharacterInfoSection(stringResource(R.string.detail_text_number_of_episodes), character.episode.size.toString())
            }
        }

    }
}

@Composable
fun CharacterHeader(character: CharacterDetailVO) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "character image",
            modifier = Modifier
                .fillMaxSize(0.5F)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = character.name, style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun CharacterInfoSection(title: String, value: String) {
    if (value.isEmpty()) return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$title:",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterDetailScreen() {
    val character = CharacterDetailVO(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        gender = "Male",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/1"),
        origin = "Earth (C-137)",
        location = "Citadel of Ricks",
        type = "",
    )
    DetailScreen(character)
}