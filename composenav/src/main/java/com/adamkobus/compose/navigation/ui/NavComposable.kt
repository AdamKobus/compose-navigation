package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.adamkobus.compose.navigation.NavigationId

/**
 * This components acts as a bridge between the owner of [navController] and the navigation state model.
 *
 * @param observedGraphs If not empty, then only NavActions that originate from the graphs included in this list will
 * be processed. All NavActions will be processed if [observedGraphs] is empty
 **/
@Composable
fun NavComposable(
    navController: NavHostController,
    observedGraphs: List<String> = emptyList(),
    navigationId: NavigationId
) {
    val vm: NavComposableVM = viewModel()
    LifecycleAwareComponent(vm)
    vm.viewParam.bind(NavComposableParam(navigationId = navigationId, graphs = observedGraphs))

    NavComposableInner(navController, vm)
}

@Composable
private fun NavComposableInner(
    navController: NavHostController,
    vm: NavComposableVM
) {
    CurrentBackStackEntryUpdater(navController, vm)
    PendingActionProcessor(navController, vm)
}

@Composable
private fun CurrentBackStackEntryUpdater(
    navController: NavHostController,
    vm: NavComposableVM
) {
    val isInitialized = vm.state.isInitialized.value
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val updateState = BackStackEntryUpdateState(isInitialized, currentBackStackEntry)

    LaunchedEffect(key1 = updateState) {
        if (isInitialized) {
            vm.processBackStackEntry(currentBackStackEntry, navController.backQueue)
        }
    }
}

@Composable
private fun PendingActionProcessor(navController: NavHostController, vm: NavComposableVM) {
    val pendingAction = vm.pendingActionState.value
    LaunchedEffect(key1 = pendingAction) {
        if (pendingAction is PendingActionState.Present) {
            val backStackModifier = pendingAction.action.navigate(navController)
            pendingAction.complete(backStackModifier)
        }
    }
}

private data class BackStackEntryUpdateState(
    val isInitialized: Boolean,
    val currentBackStackEntry: NavBackStackEntry?
)
