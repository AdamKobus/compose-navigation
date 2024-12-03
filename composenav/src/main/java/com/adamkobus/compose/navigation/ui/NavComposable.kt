package com.adamkobus.compose.navigation.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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
    navigationId: NavigationId,
) {
    val vm: NavComposableVM = viewModel(key = navController.toString())
    vm.viewParam.bind(NavComposableParam(navigationId = navigationId, graphs = observedGraphs))

    NavComposableInner(navController, vm)
}

@Composable
private fun NavComposableInner(
    navController: NavHostController,
    vm: NavComposableVM,
) {
    val backStackState = navController.currentBackStackEntryAsState()
    CurrentBackStackEntryUpdater(navController, vm, backStackState)
    PendingActionProcessor(navController, vm)
}

@SuppressLint("RestrictedApi")
@Composable
private fun CurrentBackStackEntryUpdater(
    navController: NavHostController,
    vm: NavComposableVM,
    backStackState: State<NavBackStackEntry?>,
) {
    val currentBackStackEntry = backStackState.value
    val queue = navController.currentBackStack.collectAsState().value
    val updateState = BackStackEntryUpdateState(currentBackStackEntry, queue)

    LaunchedEffect(key1 = updateState) {
        vm.processBackStackEntry(currentBackStackEntry, queue)
    }
}

@Composable
private fun PendingActionProcessor(
    navController: NavHostController,
    vm: NavComposableVM,
) {
    val pendingAction = vm.pendingActionState.value
    LaunchedEffect(key1 = pendingAction) {
        if (pendingAction is PendingActionState.Present) {
            val backStackModified = pendingAction.action.navigate(navController)
            pendingAction.complete(backStackModified)
        }
    }
}

private data class BackStackEntryUpdateState(
    val currentBackStackEntry: NavBackStackEntry?,
    val queue: List<NavBackStackEntry>,
)
