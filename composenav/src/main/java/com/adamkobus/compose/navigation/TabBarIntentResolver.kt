package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.TabStateSavingBehaviour.DONT_SAVE
import com.adamkobus.compose.navigation.TabStateSavingBehaviour.SAVE_ALL
import com.adamkobus.compose.navigation.TabStateSavingBehaviour.SAVE_START_DESTINATION
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.navActionOptions
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.ResolveResult

/**
 * Use this [NavIntentResolver] to handle edge cases related to navigation using tab bar.
 *
 * Cases handled by this resolver:
 * 1. Back stack doesn't contain tab bar root graph - no action is produced as this is not supported by this resolver
 * 2. Current destination already matches the starting destination linked of the graph to which given intent maps.
 * No action is taken in such case
 * 3. Starting destination of the graph mapped with [tabsMapping] from the processed intent is already present in back stack.
 * Back stack will be popped to starting destination.
 * 4. Current destination doesn't belong to any of the tab items' graphs. No action will be taken.
 * It can happen if someone clicked tab item at the same item as some other interaction happened which opened new flow in the app.
 * 5. Current destination belongs to different tab item. In this case navigation will happen.
 * Also, the state of the current tab will be saved depending on [tabStateSavingBehaviour]
 *
 * @param tabsMapping Assumption is that content displayed for each tab item lives in its own [NavGraph].
 * This map tells [TabBarIntentResolver] which tab item's graph should be opened for provided intents.
 *
 * @param tabsRootGraph Assumption is that tab items' graphs are nested in additional graph which is dedicated for tab host content only.
 * This is required for this resolved to function properly.
 *
 * @param popToTabHostIntent optional intent.
 * When used, [TabBarIntentResolver] will try to pop back stack to the latest destination in back stack that belongs to tab item content.
 *
 * @param tabStateSavingBehaviour Configures what parts of the tab item content should
 * [TabBarIntentResolver] attempt to preserve during navigation
 *
 * @see TabStateSavingBehaviour
 * @see NavIntentResolver
 */
@Suppress("ReturnCount")
open class TabBarIntentResolver(
    private val tabsMapping: Map<String, NavGraph>,
    private val tabsRootGraph: NavGraph,
    private val popToTabHostIntent: NavIntent? = null,
    private val tabStateSavingBehaviour: TabStateSavingBehaviour = SAVE_START_DESTINATION,
) : NavIntentResolver {
    private val allGraphs = tabsMapping.values.toSet()

    override suspend fun resolve(
        intent: NavIntent,
        navState: NavState,
    ): ResolveResult {
        if (intent == popToTabHostIntent) {
            return handlePopIntent(navState)
        }
        return resolveInternal(intent, navState)?.let {
            ResolveResult.Action(it)
        } ?: ResolveResult.None
    }

    private fun resolveInternal(
        intent: NavIntent,
        navState: NavState,
    ): NavAction? {
        val mappedGraph = tabsMapping[intent.name] ?: return null
        intent.origin?.let {
            if (!navState.isCurrent(it)) return null
        }
        val graphStartDestination = mappedGraph.startDestination()

        // no controller with provided tab host was found
        val controllerState = navState.find(tabsRootGraph) ?: return null

        // this resolver supports only navigation when tab host is already launched (as in, it handles only tab items clicks)
        val currentDest = controllerState.currentDestination?.destination ?: return null

        // this resolver supports only navigation when tab host is already launched (as in, it handles only tab items clicks)
        if (!navState.isInBackStack(tabsRootGraph)) return null

        // we're already at the destination that clicking this tab would take us to
        if (currentDest == graphStartDestination) return null

        val navOptions =
            if (currentDest.graph !in allGraphs) {
                intent.popOptions?.copy(launchSingleTop = true)
            } else {
                navActionOptions {
                    popUpTo(graphStartDestination)
                    launchSingleTop = true
                }
            }
        // Current destination doesn't belong to tab host and intent didn't provide nav options
        if (navOptions == null) return null

        // tab item's starting destination is already in back stack and we can pop back to it
        if (navState.isInBackStack(graphStartDestination)) {
            return currentDest goTo graphStartDestination withOptions
                navActionOptions {
                    popUpTo(graphStartDestination)
                    launchSingleTop = true
                }
        }

        // we know we're in tab host based on the back stack, but the graph of different tab is being displayed
        // in such situation we will pop to the root of the tab host and save state based on [tabStateSavingBehaviour]
        return currentDest goTo mappedGraph withOptions
            navActionOptions {
                popUpTo(tabsRootGraph) {
                    saveState = tabStateSavingBehaviour == SAVE_ALL ||
                        (
                            tabStateSavingBehaviour == SAVE_START_DESTINATION &&
                                currentDest == currentDest.graph.startDestination()
                            )
                }
                restoreState = true
                launchSingleTop = true
            }
    }

    private fun handlePopIntent(navState: NavState): ResolveResult {
        val controllerState = navState.find(tabsRootGraph) ?: return ResolveResult.None
        val currentDest = controllerState.currentDestination ?: return ResolveResult.None
        controllerState.backStack.findLast { it.destination.graph in allGraphs }?.let {
            val ret =
                currentDest.destination goTo it.destination withOptions
                    navActionOptions {
                        popUpTo(it.destination)
                        launchSingleTop = true
                    }
            return ret.asResult()
        } ?: return ResolveResult.None
    }
}

/**
 * @see DONT_SAVE
 * @see SAVE_START_DESTINATION
 * @see SAVE_ALL
 */
enum class TabStateSavingBehaviour {
    /**
     * The state of the graph displayed in the current tab will not be saved.
     * It will start from scratch when user navigates back to it
     */
    DONT_SAVE,

    /**
     * The state of the graph displayed in the current tab will be saved only if it's displaying starting destination.
     */
    SAVE_START_DESTINATION,

    /**
     * The state of the tabs will always be preserved when switching between them.
     */
    SAVE_ALL,
}
