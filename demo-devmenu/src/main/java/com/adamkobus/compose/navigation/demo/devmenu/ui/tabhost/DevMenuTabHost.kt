package com.adamkobus.compose.navigation.demo.devmenu.ui.tabhost

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.PreviewComponent
import kotlinx.coroutines.launch

@Composable
internal fun DevMenuTabHost(modifier: Modifier) {
    val vm: DevMenuTabHostVM = hiltViewModel()
    LifecycleAwareComponent(observer = vm)
    DevMenuTabHostContent(modifier, vm.state, vm.interactions)
}

@Composable
private fun DevMenuTabHostContent(
    modifier: Modifier,
    state: DevMenuTabHostState = DevMenuTabHostState.stub(),
    interactions: DevMenuTabHostInteractions = DevMenuTabHostInteractions.STUB
) {
    Box(modifier = modifier) {
        Crossfade(targetState = state.isVisible.value) { renderedIsVisible ->
            when (renderedIsVisible) {
                false -> EmptyTabHost()
                true -> TabHost(state, interactions.onTabSelected)
            }
        }
    }
}

@Composable
private fun EmptyTabHost() {
    Box(modifier = Modifier.fillMaxWidth())
}

@Composable
private fun TabHost(state: DevMenuTabHostState, onTabSelected: (DevMenuTabData) -> Unit) {
    val selectedIndex = state.selectedIndex.value
    val items = state.tabs.value
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = selectedIndex
    ) {
        items.forEachIndexed { index, tab ->
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
                selected = selectedIndex == index,
                onClick = {
                    scope.launch {
                        onTabSelected(tab)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun DevMenuTabHostPreview() {
    PreviewComponent {
        DevMenuTabHostContent(modifier = Modifier.fillMaxWidth())
    }
}
