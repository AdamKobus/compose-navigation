package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.data.NavGraph

/**
 * This components acts as a bridge between the owner of [navController] and the navigation state model.
 *
 * @param observedGraphs If not empty, then only NavActions that originate from the graphs included in this list will
 * be processed. All NavActions will be processed if [observedGraphs] is empty
 **/
@Composable
fun NavComposable(
    navController: NavHostController,
    vararg observedGraphs: NavGraph
) {
    val vm = hiltViewModel<NavComposableVM>()
    LifecycleAwareComponent(vm)
    vm.graphsParam.bind(observedGraphs.toList())

    val pendingAction = vm.pendingActionState.value
    LaunchedEffect(key1 = pendingAction) {
        if (pendingAction is PendingActionState.Present) {
            pendingAction.action.navigate(navController)
            pendingAction.complete()
        }
    }
    val currentBackStackEntry = navController.currentBackStackEntryFlow.collectAsState(initial = null)
    LaunchedEffect(key1 = currentBackStackEntry.value) {
        vm.processBackStackEntry(currentBackStackEntry.value)
    }
}
