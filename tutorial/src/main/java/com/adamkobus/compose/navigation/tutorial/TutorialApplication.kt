package com.adamkobus.compose.navigation.tutorial

import android.app.Application
import android.util.Log
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.tutorial.nav.OpenRandomItemIntentResolver
import com.adamkobus.compose.navigation.tutorial.nav.TutorialNavActionVerifier
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TutorialApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ComposeNavigation
            .setLogLevel(Log.VERBOSE)
            .addNavActionVerifiers(TutorialNavActionVerifier)
            .addNavIntentResolvers(OpenRandomItemIntentResolver)
    }
}
