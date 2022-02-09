package com.adamkobus.compose.navigation.demo.ui.dogslist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarActionState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.dogs.R
import com.adamkobus.compose.navigation.demo.ui.ext.onStartStop
import com.adamkobus.compose.navigation.demo.ui.nav.FromDogsList
import com.adamkobus.compose.navigation.democore.model.DogsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsListVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource,
    private val dogsSource: DogsSource
) : ViewModel(), LifecycleEventObserver {

    private val _listState = mutableStateOf<DogsListState>(DogsListState.Loading)
    val listState: State<DogsListState> = _listState

    private var dogsRefreshJob: Job? = null

    val interactions = DogsListInteractions(
        onDogsItemSelected = {
            viewModelScope.launch {
                navigationConsumer.offer(FromDogsList.ToDogDetails(it.id))
            }
        }
    )

    private val settingsAction = AppBarActionState.settings {
        viewModelScope.launch {
            navigationConsumer.offer(FromDogsList.ToSettings)
        }
    }
    private val appBarState = AnimatedAppBarState(
        titleState = AppBarTitleState(titleResId = R.string.dogs_list_title),
        actionsState = listOf(settingsAction)
    )

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStartStop(onStart = ::onStart, onStop = ::onStop)
    }

    private fun onStart() {
        cleanUp()
        setUpTopBar()
        dogsRefreshJob = viewModelScope.launch {
            dogsSource.observeDogs().collect {
                _listState.value = DogsListState.Loaded(it)
            }
        }
    }

    private fun setUpTopBar() {
        appBarStateSource.offer(appBarState)
    }

    private fun onStop() {
        cleanUp()
    }

    private fun cleanUp() {
        dogsRefreshJob?.cancel()
        dogsRefreshJob = null
    }
}
