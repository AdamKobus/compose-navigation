package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationId

internal class NavHostComponents(
    val navigationId: NavigationId,
    stateManager: NavStateManager
) {

    val navigationProcessor = NavigationProcessor(
        navigationId = navigationId,
        ioDispatcher = ComposeNavigation.getIoDispatcher(),
        stateManager = stateManager,
        navGatekeeper = ComposeNavigation.getNavGatekeeper(),
        navIntentResolver = ComposeNavigation.getNavIntentResolvingManager(),
        actionDispatcher = ComposeNavigation.createPendingActionDispatcher(),
        loggerProvider = provider { ComposeNavigation.getLogger() },
        timeoutProvider = provider { ComposeNavigation.getNavigationProcessingTimeout() }
    )
}
