package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.adamkobus.compose.navigation.destination.NavDestination

/**
 * Provides access to the destination used to render this screen.
 * It can be different from the destination that is currently at the top of the back stack.
 */
val LocalNavDestination: ProvidableCompositionLocal<NavDestination?> =
    staticCompositionLocalOf { null }
