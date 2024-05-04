package com.beettechnologies.agreena.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beettechnologies.agreena.home.presentation.HomeView

@ExperimentalAnimationApi
@Composable
fun NavigationHost(navController: NavHostController, navigation: Navigation) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeView(navigation = navigation)
        }
    }
}
