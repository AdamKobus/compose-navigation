package com.adamkobus.compose.navigation.demo.app

import android.app.Application
import android.util.Log
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.demo.nav.AppNavActionVerifier
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DemoApp : Application() {
    @Inject
    lateinit var intentResolvers: Set<@JvmSuppressWildcards NavIntentResolver>

    override fun onCreate() {
        super.onCreate()
        ComposeNavigation.setLogLevel(Log.VERBOSE)
            .addNavActionVerifiers(AppNavActionVerifier)
            .addNavIntentResolvers(intentResolvers)
    }
}
