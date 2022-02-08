package com.adamkobus.compose.navigation.destination

data class CurrentDestination(
    val destination: INavDestination?,
    val backStack: List<INavDestination>
)
