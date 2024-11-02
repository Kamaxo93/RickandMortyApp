package com.camacho.rickandmortyapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.camacho.rickandmortyapp.core.navigation.NavigationRickAndMorty
import com.camacho.rickandmortyapp.ui.home.viewmodel.HomeViewModel
import com.camacho.rickandmortyapp.ui.theme.RickandMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user_name", "Alice")
        editor.putInt("user_age", 25)
        editor.apply()

        val userName = sharedPreferences.getString("user_name", "")
        val userAge = sharedPreferences.getInt("user_age", 0)
        setContent {
            RickandMortyAppTheme {
                NavigationRickAndMorty()
            }
        }
    }
}