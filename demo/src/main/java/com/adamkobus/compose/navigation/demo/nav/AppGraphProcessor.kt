package com.adamkobus.compose.navigation.demo.nav

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.NavActionProcessor
import com.adamkobus.compose.navigation.data.GlobalDestination
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.demo.settings.nav.SettingsGraph
import com.adamkobus.compose.navigation.ext.navigate
import javax.inject.Inject

class AppGraphProcessor @Inject constructor() : NavActionProcessor {
    override fun process(action: NavAction, navController: NavHostController): Boolean {
        return when (action.fromDestination) {
            AppGraph.SplashScreen -> processFromSplash(action, navController)
            AppGraph.WelcomeScreen -> processFromWelcomeScreen(action, navController)
            AppGraph.CatsList -> processFromCatsList(action, navController)
            AppGraph.DogsList -> processFromDogsList(action, navController)
            GlobalDestination -> processFromGlobal(action, navController)
            else -> false
        }
    }

    private fun processFromSplash(action: NavAction, navController: NavHostController): Boolean {
        if (action.toDestination == AppGraph.WelcomeScreen) {
            navController.navigate(action) {
                popUpTo(AppGraph.name)
                launchSingleTop = true
            }
            return true
        }
        return false
    }

    private fun processFromWelcomeScreen(action: NavAction, navController: NavHostController): Boolean {
        val destination = action.toDestination
        if (destination == AppGraph.DogsList || destination == AppGraph.CatsList) {
            navController.navigate(action) {
                popUpTo(AppGraph.name)
                launchSingleTop = true
            }
            return true
        }
        return false
    }

    private fun processFromCatsList(action: NavAction, navController: NavHostController): Boolean {
        when (action.toDestination) {
            AppGraph.CatDetails -> navController.navigate(action) {}
            else -> return false
        }
        return true
    }

    private fun processFromDogsList(action: NavAction, navController: NavHostController): Boolean {
        when (action.toDestination) {
            AppGraph.DogDetails -> navController.navigate(action) {}
            else -> return false
        }
        return true
    }

    private fun processFromGlobal(action: NavAction, navController: NavHostController): Boolean {
        when (action.toDestination) {
            SettingsGraph -> navController.navigate(action) {}
            else -> return false
        }
        return true
    }
}
