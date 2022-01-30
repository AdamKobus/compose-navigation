package com.adamkobus.compose.navigation.ext

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.adamkobus.compose.navigation.data.NavAction

fun NavHostController.navigate(navAction: NavAction, builder: NavOptionsBuilder.() -> Unit) {
    navigate(navAction.toDestination.route.buildPath(navAction.params), builder)
}
