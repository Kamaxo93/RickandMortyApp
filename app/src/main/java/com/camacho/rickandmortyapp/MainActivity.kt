package com.camacho.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.camacho.rickandmortyapp.core.navigation.NavigationRickAndMorty
import com.camacho.rickandmortyapp.ui.theme.RickandMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickandMortyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavigationRickAndMorty()
                }
            }
        }
    }
}