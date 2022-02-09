package com.adamkobus.compose.navigation.demo.ui.tabhost

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.NavigationStateSource
import com.adamkobus.compose.navigation.demo.ui.nav.CatsBrowserGraph
import com.adamkobus.compose.navigation.demo.ui.nav.DemoIntents
import com.adamkobus.compose.navigation.demo.ui.nav.DogsBrowserGraph
import com.adamkobus.compose.navigation.destination.CurrentDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoTabsNavigationVM @Inject constructor(
    private val navigationStateSource: NavigationStateSource,
    private val navigationConsumer: NavigationConsumer
) : LifecycleAwareViewModel() {

    private val currentTabHostType = mutableStateOf(TabHostType.NONE)
    private val animalsTabHostState = mutableStateOf(
        AnimalsTabHostState(
            items = listOf(
                DemoTabs.Dogs, DemoTabs.Cats
            ).sortedBy { it.index },
            selectedIndex = 0
        )
    )

    val screenState = DemoTabsNavigationState(
        tabHostType = currentTabHostType,
        animalsTabHostState = animalsTabHostState
    )

    val interactions = DemoTabsInteractions(
        onTabSelected = {
            viewModelScope.launch {
                processTabSelection(it)
            }
        }
    )

    init {
        runOnStart {
            navigationStateSource.observeCurrentDestination().collect {
                processNavigationUpdate(it)
            }
        }
    }

    private fun processNavigationUpdate(currentDestination: CurrentDestination) {
        val destination = currentDestination.destination
        if (destination in CATS_DESTINATIONS) {
            onCatActive()
        } else if (destination in DOGS_DESTINATIONS) {
            onDogActive()
        } else {
            hideTabHost()
        }
    }

    private fun onCatActive() {
        currentTabHostType.value = TabHostType.ANIMALS
        animalsTabHostState.value = animalsTabHostState.value.copy(selectedIndex = DemoTabs.Cats.index)
    }

    private fun onDogActive() {
        currentTabHostType.value = TabHostType.ANIMALS
        animalsTabHostState.value = animalsTabHostState.value.copy(selectedIndex = DemoTabs.Dogs.index)
    }

    private fun hideTabHost() {
        currentTabHostType.value = TabHostType.NONE
    }

    private fun processTabSelection(tabData: DemoTabData) {
        when (tabData) {
            DemoTabs.Cats -> navigationConsumer.offer(DemoIntents.OpenCatsBrowser)
            DemoTabs.Dogs -> navigationConsumer.offer(DemoIntents.OpenDogsBrowser)
        }
    }

    companion object {
        private val CATS_DESTINATIONS = listOf(CatsBrowserGraph.CatsList, CatsBrowserGraph.CatDetails)
        private val DOGS_DESTINATIONS = listOf(DogsBrowserGraph.DogDetails, DogsBrowserGraph.DogsList, DogsBrowserGraph.DemoDialog)
    }
}
