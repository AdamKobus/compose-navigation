package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    LifecycleAwareComponent(vm)
    vm.viewParam.bind(NavComposableParam(navigationId = navigationId, graphs = observedGraphs))

    val pendingAction = vm.pendingActionState.value
    LaunchedEffect(key1 = pendingAction) {
        if (pendingAction is PendingActionState.Present) {
            val backStackModifier = pendingAction.action.navigate(navController)
            pendingAction.complete(backStackModifier)
        }
    }
    LaunchedEffect(key1 = currentBackStackEntry.value) {
        vm.processBackStackEntry(currentBackStackEntry.value, navController.backQueue)
    }
}
