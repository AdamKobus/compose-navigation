package com.adamkobus.compose.navigation.demo.ui.welcome

data class WelcomeScreenInteractions(
    val onDogsSelected: () -> Unit = {},
    val onCatsSelected: () -> Unit = {},
)
