package com.adamkobus.compose.navigation.demo.nav.tabhost

import com.adamkobus.compose.navigation.TabBarIntentResolver
import com.adamkobus.compose.navigation.TabStateSavingBehaviour
import com.adamkobus.compose.navigation.demo.ui.nav.AppRootGraph
import com.adamkobus.compose.navigation.demo.ui.nav.CatsBrowserGraph
import com.adamkobus.compose.navigation.demo.ui.nav.DemoIntents
import com.adamkobus.compose.navigation.demo.ui.nav.DogsBrowserGraph
import javax.inject.Inject

class DemoTabBarIntentResolver @Inject constructor() : TabBarIntentResolver(
    mapOf(
        DemoIntents.OpenDogsBrowser.name to DogsBrowserGraph,
        DemoIntents.OpenCatsBrowser.name to CatsBrowserGraph
    ),
    AppRootGraph,
    tabStateSavingBehaviour = TabStateSavingBehaviour.SAVE_START_DESTINATION
)
