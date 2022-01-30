package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

/**
 * This components acts as a bridge between the view layer that operates with [NavHostController] and model.
 */
@Composable
fun NavComposable(navController: NavHostController) {
    val vm = hiltViewModel<NavComposableVM>()
    LifecycleAwareComponent(vm)
    val pendingActions = vm.pendingActions.value
    LaunchedEffect(key1 = pendingActions) {
        vm.processNavActions(navController = navController)
    }
    val currentBackStackEntry = navController.currentBackStackEntryFlow.collectAsState(initial = null)
    LaunchedEffect(key1 = currentBackStackEntry.value) {
        vm.processBackStackEntry(currentBackStackEntry.value)
    }
}
