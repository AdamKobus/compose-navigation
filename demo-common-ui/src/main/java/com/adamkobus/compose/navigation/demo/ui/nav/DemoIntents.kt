package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.intent.NavIntent

object DemoIntents {
    const val OPEN_DEV_MENU = "demo.OpenDevMenu"
    const val CLOSE_DEV_MENU = "demo.CloseDevMenu"
    const val RESTART_APP = "demo.RestartApp"

    val OpenDogsBrowser = NavIntent("openDogsBrowser")
    val OpenCatsBrowser = NavIntent("openCatsBrowser")

    fun restartApp(origin: NavDestination) = NavIntent(RESTART_APP, origin = origin)
}
