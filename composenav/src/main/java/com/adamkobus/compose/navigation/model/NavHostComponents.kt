package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavigationId

internal class NavHostComponents(val navigationId: NavigationId) {

    val navigationProcessor = NavigationProcessor(navigationId)
    val destinationManager = NavStateManager(navigationId)
}
