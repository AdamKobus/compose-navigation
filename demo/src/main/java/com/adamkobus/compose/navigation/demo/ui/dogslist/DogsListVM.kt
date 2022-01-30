package com.adamkobus.compose.navigation.demo.ui.dogslist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.demo.R
import com.adamkobus.compose.navigation.demo.nav.FromDogsList
import com.adamkobus.compose.navigation.demo.nav.FromGlobal
import com.adamkobus.compose.navigation.demo.ui.Elevation
import com.adamkobus.compose.navigation.demo.ui.ext.onStartStop
import com.adamkobus.compose.navigation.demo.ui.topbar.DemoColors
import com.adamkobus.compose.navigation.demo.ui.topbar.TopBarStateSource
import com.adamkobus.compose.navigation.democore.model.DogsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsListVM @Inject constructor(
    private val navActionConsumer: NavActionConsumer,
    private val topBarStateSource: TopBarStateSource,
    private val dogsSource: DogsSource
) : ViewModel(), LifecycleEventObserver {

    private val _listState = mutableStateOf<DogsListState>(DogsListState.Loading)
    val listState: State<DogsListState> = _listState

    private var dogsRefreshJob: Job? = null

    val interactions = DogsListInteractions(
        onDogsItemSelected = {
            navActionConsumer.offer(FromDogsList.ToDogDetails(it.id))
        }
    )

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStartStop(onStart = ::onStart, onStop = ::onStop)
    }

    private fun onStart() {
        cleanUp()
        topBarStateSource.setUpTopBar {
            titleResId = R.string.dogs_list_title
            elevation = Elevation.AppBar
            background = DemoColors.PrimarySurface
            action {
                icon = Icons.Filled.Settings
                onClick = { onSettingsSelected() }
            }
        }
        dogsRefreshJob = viewModelScope.launch {
            dogsSource.observeDogs().collect {
                _listState.value = DogsListState.Loaded(it)
            }
        }
    }

    private fun onStop() {
        cleanUp()
    }

    private fun cleanUp() {
        dogsRefreshJob?.cancel()
        dogsRefreshJob = null
    }

    private fun onSettingsSelected() {
        viewModelScope.launch {
            navActionConsumer.offer(FromGlobal.ToSettings)
        }
    }
}
