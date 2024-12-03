package com.adamkobus.compose.navigation.tutorial.nav

import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.intent.NavIntent

object TutorialIntents {
    const val OPEN_RANDOM_ITEM_INTENT = "openRandomItem"

    fun openRandomItem(origin: NavDestination) = NavIntent(name = OPEN_RANDOM_ITEM_INTENT, origin = origin)
}
