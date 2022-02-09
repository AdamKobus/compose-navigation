package com.adamkobus.compose.navigation.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.adamkobus.compose.navigation.tutorial.nav.TutorialGraph
import com.adamkobus.compose.navigation.tutorial.nav.tutorialGraph
import com.adamkobus.compose.navigation.tutorial.ui.theme.ComposeNavigationTutorialTheme
import com.adamkobus.compose.navigation.ui.NavComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTutorialTheme {
                val navHostController = rememberAnimatedNavController()
                NavComposable(navController = navHostController)

                AnimatedNavHost(
                    navController = navHostController,
                    startDestination = TutorialGraph.name,
                    modifier = Modifier.fillMaxSize()
                ) {
                    tutorialGraph()
                }
            }
        }
    }
}
