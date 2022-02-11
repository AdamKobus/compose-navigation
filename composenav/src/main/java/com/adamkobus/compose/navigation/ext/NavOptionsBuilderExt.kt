package com.adamkobus.compose.navigation.ext

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.PopDestination

/**
 * @throws UnsupportedOperationException when used with [PopDestination]
 *
 * @see [NavOptionsBuilder.popUpTo]
 */
fun NavOptionsBuilder.popUpTo(dest: INavDestination, inclusive: Boolean = false, saveState: Boolean = false) {
    verifyIfNotPopDestination(dest)
    popUpTo(dest.route.buildRoute(), createBuilder(inclusive, saveState))
}

/**
 * @throws UnsupportedOperationException when used with [PopDestination]
 *
 * @see [NavOptionsBuilder.popUpTo]
 */
fun NavOptionsBuilder.popUpTo(dest: INavDestination, builder: PopUpToBuilder.() -> Unit) {
    verifyIfNotPopDestination(dest)
    popUpTo(dest.route.buildRoute(), builder)
}

private fun createBuilder(inclusiveValue: Boolean, saveStateValue: Boolean): (PopUpToBuilder.() -> Unit) {
    return {
        inclusive = inclusiveValue
        saveState = saveStateValue
    }
}

private fun verifyIfNotPopDestination(dest: INavDestination) {
    if (dest is PopDestination) {
        throw UnsupportedOperationException("Cannot use popUpTo with PopDestination")
    }
}
