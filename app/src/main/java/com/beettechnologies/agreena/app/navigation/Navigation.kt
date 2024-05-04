package com.beettechnologies.agreena.app.navigation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.navigation.NavController

interface Navigation {
    fun setController(controller: NavController)
    fun navigateToHome()
    fun navigateBack()
}

class NavigationPreviewParameterProvider : PreviewParameterProvider<Navigation> {
    override val values: Sequence<Navigation> = sequenceOf(dummyNavigation)
}

val dummyNavigation = object : Navigation {
    override fun setController(controller: NavController) {
    }

    override fun navigateToHome() {
    }

    override fun navigateBack() {
    }
}