package com.adamkobus.compose.navigation.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.adamkobus.compose.navigation.ComposeNavHost
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.tutorial.nav.TutorialGraph
import com.adamkobus.compose.navigation.tutorial.nav.tutorialGraph
import com.adamkobus.compose.navigation.tutorial.ui.theme.ComposeNavigationTutorialTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTutorialTheme {
                val controller = rememberAnimatedNavController()
                ComposeNavHost(
                    startGraph = TutorialGraph,
                    controller = controller,
                    navigationId = NavigationId.DEFAULT,
                    modifier = Modifier.fillMaxSize()
                ) {
                    tutorialGraph()
                }
            }
        }
    }
}
