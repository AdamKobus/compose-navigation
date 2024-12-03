package com.adamkobus.compose.navigation.poc.multinavhost

import android.app.Application
import android.util.Log
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.VerifyResult
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.GlobalGraph
import com.adamkobus.compose.navigation.destination.NavState
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PocApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ComposeNavigation.setLogLevel(Log.VERBOSE)
            .addNavActionVerifiers(PocNavActionVerifier)
    }
}

private object PocNavActionVerifier : NavActionVerifier {
    override fun isNavActionAllowed(
        navState: NavState,
        action: NavAction,
    ): VerifyResult {
        if (action.fromDestination.graph == GlobalGraph) return VerifyResult.Allow
        return if (navState.isCurrent(action.fromDestination)) {
            VerifyResult.Allow
        } else {
            VerifyResult.Discard
        }
    }
}
