package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}
data class Artwork(
    val id: Int,
    val imageResId: Int,
    val title: String,
    val artist: String,
    val year: String
)
@Composable
fun ArtSpaceApp() {
    var currentArtwork by remember { mutableStateOf(artworks.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Artwork Section
        Image(
            painter = painterResource(id = currentArtwork.imageResId),
            contentDescription = null, // Provide a meaningful content description
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp), // Ajusta la altura según tus necesidades
            contentScale = ContentScale.Crop
        )

        // Information Section
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Title: ${currentArtwork.title}", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Artist: ${currentArtwork.artist}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Year: ${currentArtwork.year}", style = MaterialTheme.typography.body1)
        }

        // Interactive Buttons
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                currentArtwork = getNextArtwork(currentArtwork.id)
            }) {
                Text("Next")
            }

            Button(onClick = {
                currentArtwork = getPreviousArtwork(currentArtwork.id)
            }) {
                Text("Previous")
            }
        }
    }
}

fun getNextArtwork(currentId: Int): Artwork {
    val nextId = (currentId % artworks.size) + 1
    return artworks.first { it.id == nextId }
}

fun getPreviousArtwork(currentId: Int): Artwork {
    val previousId = if (currentId == 1) artworks.size else currentId - 1
    return artworks.first { it.id == previousId }
}

fun generateArtworks(): List<Artwork> {
    return listOf(
        Artwork(1, R.drawable.cuadro_moderno_minimalista_705570, "Artwork 1", "Artist 1", "2022"),
        Artwork(2, R.drawable.ic_launcher_background, "Artwork 2", "Artist 2", "2023"),
        Artwork(3, R.drawable.ic_launcher_foreground, "Artwork 3", "Artist 3", "2024")
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}