package com.adamkobus.compose.navigation.tutorial

import android.app.Application
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.tutorial.nav.TutorialNavActionVerifier
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TutorialApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ComposeNavigation
            .addNavActionVerifiers(TutorialNavActionVerifier)
    }
}
