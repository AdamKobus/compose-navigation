package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.TabStateSavingBehaviour.DONT_SAVE
import com.adamkobus.compose.navigation.TabStateSavingBehaviour.SAVE_ALL
import com.adamkobus.compose.navigation.TabStateSavingBehaviour.SAVE_START_DESTINATION
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.navActionOptions
import com.adamkobus.compose.navigation.destination.CurrentDestination
import com.adamkobus.compose.navigation.destination.NavGraph
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
    private val tabsMapping: Map<NavIntent, NavGraph>,
    private val tabsRootGraph: NavGraph,
    private val popToTabHostIntent: NavIntent? = null,
    private val tabStateSavingBehaviour: TabStateSavingBehaviour = SAVE_START_DESTINATION
) : NavIntentResolver {

    private val allGraphs = tabsMapping.values.toSet()

    override suspend fun resolve(intent: NavIntent, currentDestination: CurrentDestination): ResolveResult {
        if (intent == popToTabHostIntent) {
            return handlePopIntent(currentDestination)
        }
        return resolveInternal(intent, currentDestination)?.let {
            ResolveResult.Action(it)
        } ?: ResolveResult.None
    }

    private fun resolveInternal(intent: NavIntent, currentDestination: CurrentDestination): NavAction? {
        val mappedGraph = tabsMapping[intent] ?: return null
        val graphStartDestination = mappedGraph.startDestination()

        // this resolver supports only navigation when tab host is already launched (as in, it handles only tab items clicks)
        val currentDest = currentDestination.destination ?: return null

        // this resolver supports only navigation when tab host is already launched (as in, it handles only tab items clicks)
        if (!currentDestination.isInBackStack(tabsRootGraph)) return null

        // we're already at the destination that clicking this tab would take us to
        if (currentDest == graphStartDestination) return null

        // Current destination doesn't belong to tab host at all
        if (currentDest.graph !in allGraphs) return null

        // tab item's starting destination is already in back stack and we can pop back to it
        if (graphStartDestination in currentDestination.backStack) {
            return currentDest goTo graphStartDestination withOptions navActionOptions {
                popUpTo(graphStartDestination)
                launchSingleTop = true
            }
        }

        // we know we're in tab host based on the back stack, but the graph of different tab is being displayed
        // in such situation we will pop to the root of the tab host and save state based on [tabStateSavingBehaviour]
        return currentDest goTo mappedGraph withOptions navActionOptions {
            popUpTo(tabsRootGraph) {
                saveState = tabStateSavingBehaviour == SAVE_ALL ||
                    (tabStateSavingBehaviour == SAVE_START_DESTINATION && currentDest == currentDest.graph.startDestination())
            }
            restoreState = true
            launchSingleTop = true
        }
    }

    private fun handlePopIntent(currentDestination: CurrentDestination): ResolveResult {
        val currentDest = currentDestination.destination ?: return ResolveResult.None
        currentDestination.backStack.findLast { it.graph in allGraphs }?.let {
            val ret = currentDest goTo it withOptions navActionOptions {
                popUpTo(it)
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
     * The state of the graph displayed in the current tab will not be saved. It will start from scratch when user navigates back to it
     */
    DONT_SAVE,

    /**
     * The state of the graph displayed in the current tab will be saved only if it's displaying starting destination.
     */
    SAVE_START_DESTINATION,

    /**
     * The state of the tabs will always be preserved when switching between them.
     */
    SAVE_ALL
}
