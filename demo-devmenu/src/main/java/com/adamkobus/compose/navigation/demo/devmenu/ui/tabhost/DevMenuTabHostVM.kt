package com.adamkobus.compose.navigation.demo.devmenu.ui.tabhost

import androidx.compose.runtime.mutableStateOf
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.NavigationStateSource
import com.adamkobus.compose.navigation.demo.devmenu.nav.DevMenuGraph
import com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost.DevMenuInfoGraph
import com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost.DevMenuSettingsGraph
import com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost.DevMenuTabHostIntents
import com.adamkobus.compose.navigation.destination.NavState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DevMenuTabHostVM @Inject constructor(
    private val navigationStateSource: NavigationStateSource,
    private val navigationConsumer: NavigationConsumer,
) : LifecycleAwareViewModel() {
    private val isVisible = mutableStateOf(false)
    private val selectedIndex = mutableStateOf(0)
    private val tabs =
        mutableStateOf(
            listOf(
                DevMenuTabs.Info,
                DevMenuTabs.Settings,
            ),
        )
    val state =
        DevMenuTabHostState(
            tabs = tabs,
            isVisible = isVisible,
            selectedIndex = selectedIndex,
        )

    val interactions =
        DevMenuTabHostInteractions(
            onTabSelected = {
                processTabSelection(it)
            },
        )

    init {
        runOnStart {
            navigationStateSource.observeNavState().collect {
                processNavigationUpdate(it)
            }
        }
    }

    private fun processNavigationUpdate(navState: NavState) {
        val controllerState = navState.find(DevMenuGraph)
        when {
            controllerState == null -> hideTabHost()
            controllerState.isInBackStack(DevMenuInfoGraph) -> onInfoActive()
            controllerState.isInBackStack(DevMenuSettingsGraph) -> onSettingsActive()
            else -> hideTabHost()
        }
    }

    private fun onInfoActive() {
        isVisible.value = true
        selectedIndex.value = DevMenuTabs.Info.index
    }

    private fun onSettingsActive() {
        isVisible.value = true
        selectedIndex.value = DevMenuTabs.Settings.index
    }

    private fun hideTabHost() {
        isVisible.value = false
    }

    private fun processTabSelection(tabData: DevMenuTabData) {
        when (tabData) {
            DevMenuTabs.Info -> navigationConsumer.offer(DevMenuTabHostIntents.OpenInfo)
            DevMenuTabs.Settings -> navigationConsumer.offer(DevMenuTabHostIntents.OpenSettings)
        }
    }
}
