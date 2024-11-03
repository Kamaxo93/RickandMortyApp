package com.camacho.rickandmortyapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.camacho.rickandmortyapp.ui.detail.screen.DetailScreen
import com.camacho.rickandmortyapp.ui.home.screen.HomeScreen


@Composable
fun NavigationRickAndMorty() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {

        composable<Home> {
            HomeScreen { id -> navController.navigate(Detail(id = id)) }
        }

        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            DetailScreen(id = detail.id)
        }
    }
}
