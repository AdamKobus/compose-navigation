package com.adamkobus.compose.navigation.demo.ui.nav

import androidx.navigation.NavGraphBuilder
import javax.inject.Inject

/**
 * Introduced just so that Dagger doesn't complain when there is on [NavGraphApplier] declared in the app
 */
internal class StubNavGraphApplier @Inject constructor() : NavGraphApplier {
    override fun applyNavGraph(builder: NavGraphBuilder) {
        // do nothing
    }
}
