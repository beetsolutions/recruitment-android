package com.beettechnologies.agreena.app.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
}
