package com.adamkobus.compose.navigation.demo.devmenu.nav

import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.ui.nav.NavGraphApplier
import javax.inject.Inject

internal class DevMenuGraphApplier @Inject constructor() : NavGraphApplier {
    override fun applyNavGraph(builder: NavGraphBuilder) {
        builder.devMenuRootGraph()
    }
}
