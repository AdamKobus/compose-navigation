package com.adamkobus.compose.navigation.action

import androidx.navigation.AnimBuilder
import androidx.navigation.navOptions
import com.adamkobus.compose.navigation.destination.NavDestination

/**
 * Represents options that can be used when navigating to a new destination. Use [navActionOptions] to create new instance of [NavOptions]
 *
 * @property launchSingleTop If set to true, then new screen won't be added to back stack if the target destination is already preset in it.
 * See [androidx.navigation.NavOptionsBuilder.launchSingleTop]
 * @property restoreState If set to true, then the state of the new screen will be restored
 * given that it was previously saved when removing it from back stack. See [androidx.navigation.NavOptionsBuilder.restoreState]
 * @property popOptions If not null, then back stack will be popped before navigating to a new screen.
 * [PopOptions] are used to configure the pop behaviour.
 */
data class NavOptions internal constructor(
    val launchSingleTop: Boolean,
    val restoreState: Boolean,
    val popOptions: PopOptions?
) {
    /**
     * [NavOptions] were introduced for internal use by Compose Navigation library.
     * This function converts them into [androidx.navigation.NavOptions].
     *
     * @return NavOptions compatible with Jetpack's Navigation
     */
    fun toAndroidNavOptions(): androidx.navigation.NavOptions {
        return navOptions {
            launchSingleTop = this@NavOptions.launchSingleTop
            this@NavOptions.popOptions?.let {
                popUpTo(it.destination.route.buildRoute()) {
                    saveState = it.saveState
                    inclusive = it.inclusive
                }
            }
        }
    }
}

/**
 * Used to construct [NavOptions]. It's similar to Android's [androidx.navigation.NavOptionsBuilder].
 * The only difference is custom [PopOptionsBuilder]
 *
 * @property launchSingleTop see [androidx.navigation.NavOptionsBuilder.launchSingleTop]
 * @property restoreState see [androidx.navigation.NavOptionsBuilder.restoreState]
 */
data class NavOptionsBuilder internal constructor(
    var launchSingleTop: Boolean = false,
    var restoreState: Boolean = false,
    private var popOptions: PopOptions? = null,
    private var animBuilder: AnimBuilder? = null
) {

    /**
     * Creates [PopOptions]
     *
     * @param destination Destination to which the back stack should be popped.
     *
     * @see [androidx.navigation.NavOptionsBuilder.popUpTo]
     */
    fun popUpTo(destination: NavDestination) {
        popUpTo(destination = destination) {}
    }

    /**
     * Creates [PopOptions]
     *
     * @param destination Destination to which the back stack should be popped.
     * @param init Type-safe builder init
     *
     * @see [androidx.navigation.NavOptionsBuilder.popUpTo]
     */
    fun popUpTo(destination: NavDestination, init: PopOptionsBuilder.() -> Unit) {
        this.popOptions = PopOptionsBuilder(destination).apply(init).build()
    }

    /**
     * Clears [PopOptions] that were previously set with [NavOptionsBuilder.popUpTo]
     */
    fun clearPop() {
        popOptions = null
    }

    /**
     * Clears [AnimBuilder] that was previously set with [NavOptionsBuilder.anim]
     */
    fun clearAnim() {
        animBuilder = null
    }

    /**
     * Provides a way to set up custom animation options
     *
     * @param init Type-safe builder init
     *
     * @see [androidx.navigation.AnimBuilder]
     */
    fun anim(init: AnimBuilder.() -> Unit) {
        this.animBuilder = AnimBuilder().apply(init)
    }

    internal fun build(): NavOptions =
        NavOptions(
            launchSingleTop = launchSingleTop,
            restoreState = restoreState,
            popOptions = popOptions
        )
}

/**
 * Used to define pop behaviour when navigating.
 *
 * @property destination Destination to which the back stack should be popped before navigating.
 * See [androidx.navigation.PopUpToBuilder.popUpToRoute]
 *
 * @property saveState If set to true, then any screens that are removed from back stack will have their state saved beforehand.
 * Their state can be later restored if [NavOptions.restoreState] flag is set to true when navigating back to them.
 * See [androidx.navigation.PopUpToBuilder.saveState]
 *
 * @property inclusive If set to true, then [destination] will be included in the screens that are removed from back stack.
 * See [androidx.navigation.PopUpToBuilder.inclusive]
 */
data class PopOptions internal constructor(
    val destination: NavDestination,
    val saveState: Boolean,
    val inclusive: Boolean
)

/**
 * Builder for [PopOptions]
 *
 * @property destination Destination to which the back stack should be popped before navigating.
 * See [androidx.navigation.PopUpToBuilder.popUpToRoute]
 *
 * @property saveState If set to true, then any screens that are removed from back stack will have their state saved beforehand.
 * Their state can be later restored if [NavOptions.restoreState] flag is set to true when navigating back to them.
 * See [androidx.navigation.PopUpToBuilder.saveState]
 *
 * @property inclusive If set to true, then [destination] will be included in the screens that are removed from back stack.
 * See [androidx.navigation.PopUpToBuilder.inclusive]
 *
 * @see PopOptions
 * @see NavOptions
 * @see [androidx.navigation.NavOptions]
 * @see [androidx.navigation.PopUpToBuilder]
 */
data class PopOptionsBuilder internal constructor(
    var destination: NavDestination,
    var saveState: Boolean = false,
    var inclusive: Boolean = false
) {
    internal fun build(): PopOptions =
        PopOptions(
            destination = destination,
            saveState = saveState,
            inclusive = inclusive
        )
}

/**
 * Creates a set of options to be used when navigating to new destination.
 *
 * @param init Type-safe builder init
 *
 * @see NavOptions
 * @see NavOptionsBuilder
 */
fun navActionOptions(init: NavOptionsBuilder.() -> Unit): NavOptions =
    NavOptionsBuilder().apply(init).build()
