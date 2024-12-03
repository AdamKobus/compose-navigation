package com.adamkobus.compose.navigation.tutorial.nav

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.ResolveResult
import kotlin.random.Random

object OpenRandomItemIntentResolver : NavIntentResolver {
    private val random = Random.Default

    override suspend fun resolve(
        intent: NavIntent,
        navState: NavState,
    ): ResolveResult =
        intent.origin?.takeIf {
            intent.name == TutorialIntents.OPEN_RANDOM_ITEM_INTENT
        }?.let { origin ->
            // TutorialNavActionVerifier will be able to process this action properly
            // thanks to using intent's origin as source destination for created nav action
            ResolveResult.Action(origin goTo TutorialGraph.Detail arg random.nextInt())
        } ?: ResolveResult.None
}
