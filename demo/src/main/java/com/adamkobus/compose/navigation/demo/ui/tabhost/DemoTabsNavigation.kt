package com.adamkobus.compose.navigation.demo.ui.tabhost

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import kotlinx.coroutines.launch

@Composable
fun DemoTabsNavigation(modifier: Modifier = Modifier) {
    val vm: DemoTabsNavigationVM = hiltViewModel()
    LifecycleAwareComponent(observer = vm)
    val state = vm.screenState
    val interactions = vm.interactions
    DemoTabsNavigationContent(
        state,
        interactions = interactions,
        modifier = modifier
    )
}

@Composable
private fun DemoTabsNavigationContent(
    state: DemoTabsNavigationState,
    interactions: DemoTabsInteractions,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Crossfade(targetState = state.tabHostType.value) { renderedState ->
            when (renderedState) {
                TabHostType.NONE -> EmptyTabHost()
                TabHostType.ANIMALS -> AnimalsTabHost(state.animalsTabHostState.value, interactions.onTabSelected)
            }
        }
    }
}

@Composable
fun EmptyTabHost() {
    Box(modifier = Modifier.fillMaxWidth())
}

@Composable
fun AnimalsTabHost(state: AnimalsTabHostState, onTabSelected: (DemoTabData) -> Unit) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = state.selectedIndex
    ) {
        state.items.forEachIndexed { index, tab ->
            LeadingIconTab(
                icon = {
                    Icon(
                        painter = painterResource(id = tab.iconResId),
                        contentDescription = null
                    )
                },
                text = {
                    Text(text = stringResource(tab.titleResId))
                },
                selected = state.selectedIndex == index,
                onClick = {
                    scope.launch {
                        onTabSelected(tab)
                    }
                }
            )
        }
    }
}
