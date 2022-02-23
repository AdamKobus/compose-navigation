package com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost

import com.adamkobus.compose.navigation.TabBarIntentResolver
import com.adamkobus.compose.navigation.TabStateSavingBehaviour
import com.adamkobus.compose.navigation.demo.devmenu.nav.DevMenuGraph
import javax.inject.Inject

internal class DevMenuTabHostIntentResolver @Inject constructor() : TabBarIntentResolver(
    tabsMapping = mapOf(
        DevMenuTabHostIntents.OpenInfo to DevMenuInfoGraph,
        DevMenuTabHostIntents.OpenSettings to DevMenuSettingsGraph
    ),
    tabStateSavingBehaviour = TabStateSavingBehaviour.SAVE_ALL,
    tabsRootGraph = DevMenuGraph
)
