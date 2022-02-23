package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.intent.NavIntent

object DemoIntents {
    const val OPEN_DEV_MENU = "demo.OpenDevMenu"
    const val CLOSE_DEV_MENU = "demo.CloseDevMenu"

    val OpenDogsBrowser = NavIntent("openDogsBrowser")
    val OpenCatsBrowser = NavIntent("openCatsBrowser")
}
