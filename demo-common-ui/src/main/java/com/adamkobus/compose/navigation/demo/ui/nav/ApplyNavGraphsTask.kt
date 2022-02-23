package com.adamkobus.compose.navigation.demo.ui.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import javax.inject.Inject

class ApplyNavGraphsTask @Inject constructor(
    private val graphs: Set<@JvmSuppressWildcards NavGraphApplier>
) {
    @OptIn(ExperimentalAnimationApi::class)
    fun apply(builder: NavGraphBuilder) {
        graphs.forEach {
            it.applyNavGraph(builder)
        }
    }
}
