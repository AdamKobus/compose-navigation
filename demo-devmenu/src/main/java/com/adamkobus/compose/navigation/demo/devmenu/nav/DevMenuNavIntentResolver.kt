package com.adamkobus.compose.navigation.demo.devmenu.nav

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.action.navActionOptions
import com.adamkobus.compose.navigation.demo.ui.nav.DemoIntents
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.ResolveResult
import javax.inject.Inject

internal class DevMenuNavIntentResolver @Inject constructor() : NavIntentResolver {
    override suspend fun resolve(intent: NavIntent, navState: NavState): ResolveResult =
        when (intent.name) {
            DemoIntents.OPEN_DEV_MENU -> openDevMenu(intent, navState)
            DemoIntents.CLOSE_DEV_MENU -> closeDevMenu(navState)
            else -> ResolveResult.None
        }

    private fun openDevMenu(intent: NavIntent, navState: NavState): ResolveResult {
        if (navState.isInBackStack(DevMenuRootGraph)) return ResolveResult.None

        return intent.navigationId?.let {
            navState.get(it)
        }?.takeIf {
            !it.isInBackStack(DevMenuRootGraph)
        }?.currentDestination?.destination?.let {
            val action = it goTo DevMenuRootGraph withOptions navActionOptions {
                launchSingleTop = true
            }
            return action.asResult()
        } ?: ResolveResult.None
    }

    private fun closeDevMenu(navState: NavState): ResolveResult =
        navState.find(DevMenuRootGraph)
            ?.currentDestination
            ?.destination
            ?.popUpTo(DevMenuRootGraph, inclusive = true)
            ?.asResult() ?: ResolveResult.None
}
