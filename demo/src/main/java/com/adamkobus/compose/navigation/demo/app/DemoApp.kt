package com.adamkobus.compose.navigation.demo.app

import android.app.Application
import android.util.Log
import com.adamkobus.compose.navigation.ComposeNavigation
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ComposeNavigation.setLogLevel(Log.VERBOSE)
    }
}
