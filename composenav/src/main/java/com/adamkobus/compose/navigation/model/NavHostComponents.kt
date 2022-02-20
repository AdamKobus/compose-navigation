package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationId

internal class NavHostComponents(
    val navigationId: NavigationId
) {
    val destinationManager = NavStateManager(navigationId)

    val navigationProcessor = NavigationProcessor(
        navigationId = navigationId,
        ioDispatcher = ComposeNavigation.getIoDispatcher(),
        stateManager = destinationManager,
        navGatekeeper = ComposeNavigation.getNavGatekeeper(),
        navIntentResolver = ComposeNavigation.getNavIntentResolvingManager(),
        actionDispatcher = ComposeNavigation.createPendingActionDispatcher(),
        loggerProvider = provider { ComposeNavigation.getLogger() },
        timeoutProvider = provider { ComposeNavigation.getNavigationProcessingTimeout() }
    )
}
