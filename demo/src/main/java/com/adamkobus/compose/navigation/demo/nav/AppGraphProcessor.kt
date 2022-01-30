package com.adamkobus.compose.navigation.demo.nav

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.NavActionProcessor
import com.adamkobus.compose.navigation.data.GlobalDestination
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.ext.navigate
import javax.inject.Inject

class AppGraphProcessor @Inject constructor() : NavActionProcessor {
    override fun process(action: NavAction, navController: NavHostController): Boolean {
        return when (action.fromDestination) {
            Destinations.SplashScreen -> processFromSplash(action, navController)
            Destinations.WelcomeScreen -> processFromWelcomeScreen(action, navController)
            Destinations.CatsList -> processFromCatsList(action, navController)
            Destinations.DogsList -> processFromDogsList(action, navController)
            GlobalDestination -> processFromGlobal(action, navController)
            else -> false
        }
    }

    private fun processFromSplash(action: NavAction, navController: NavHostController): Boolean {
        if (action.toDestination == Destinations.WelcomeScreen) {
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
        if (destination == Destinations.DogsList || destination == Destinations.CatsList) {
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
            Destinations.CatDetails -> navController.navigate(action) {}
            else -> return false
        }
        return true
    }

    private fun processFromDogsList(action: NavAction, navController: NavHostController): Boolean {
        when (action.toDestination) {
            Destinations.DogDetails -> navController.navigate(action) {}
            else -> return false
        }
        return true
    }

    private fun processFromGlobal(action: NavAction, navController: NavHostController): Boolean {
        when (action.toDestination) {
            Destinations.Settings -> navController.navigate(action) {}
            else -> return false
        }
        return true
    }
}
