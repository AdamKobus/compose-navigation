package com.adamkobus.compose.navigation.demo.app

import android.app.Application
import android.util.Log
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.TabBarIntentResolver
import com.adamkobus.compose.navigation.TabStateSavingBehaviour
import com.adamkobus.compose.navigation.demo.nav.AppNavActionVerifier
import com.adamkobus.compose.navigation.demo.nav.PetsGraph
import com.adamkobus.compose.navigation.demo.ui.nav.CatsBrowserGraph
import com.adamkobus.compose.navigation.demo.ui.nav.DemoIntents
import com.adamkobus.compose.navigation.demo.ui.nav.DogsBrowserGraph
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ComposeNavigation.setLogLevel(Log.VERBOSE)
            .addNavActionVerifiers(AppNavActionVerifier)
            .addNavIntentResolvers(
                TabBarIntentResolver(
                    mapOf(
                        DemoIntents.OpenDogsBrowser to DogsBrowserGraph,
                        DemoIntents.OpenCatsBrowser to CatsBrowserGraph
                    ),
                    PetsGraph,
                    tabStateSavingBehaviour = TabStateSavingBehaviour.SAVE_START_DESTINATION
                )
            )
    }
}
