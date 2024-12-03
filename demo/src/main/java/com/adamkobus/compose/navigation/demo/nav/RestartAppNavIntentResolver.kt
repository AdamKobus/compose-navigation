package com.adamkobus.compose.navigation.demo.nav

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.demo.ui.nav.AppGraph
import com.adamkobus.compose.navigation.demo.ui.nav.AppRootGraph
import com.adamkobus.compose.navigation.demo.ui.nav.DemoIntents
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.ResolveResult
import javax.inject.Inject

class RestartAppNavIntentResolver @Inject constructor() : NavIntentResolver {
    override suspend fun resolve(
        intent: NavIntent,
        navState: NavState,
    ): ResolveResult =
        intent.takeIf { it.name == DemoIntents.RESTART_APP }?.origin?.let {
            val action =
                it.goTo(AppGraph.SplashScreen).setNavOptions {
                    popUpTo(AppRootGraph)
                }
            action.asResult()
        } ?: ResolveResult.None
}
