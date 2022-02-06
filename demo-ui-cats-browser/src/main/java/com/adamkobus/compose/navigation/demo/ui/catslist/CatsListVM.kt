package com.adamkobus.compose.navigation.demo.ui.catslist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarActionState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.cats.R
import com.adamkobus.compose.navigation.demo.ui.ext.onStartStop
import com.adamkobus.compose.navigation.demo.ui.nav.FromCatsList
import com.adamkobus.compose.navigation.democore.model.CatsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsListVM @Inject constructor(
    private val navActionConsumer: NavActionConsumer,
    private val appBarStateSource: AppBarStateSource,
    private val catsSource: CatsSource
) : ViewModel(), LifecycleEventObserver {

    private val _listState = mutableStateOf<CatsListState>(CatsListState.Loading)
    val listState: State<CatsListState> = _listState

    private val settingsAction = AppBarActionState.settings {
        viewModelScope.launch {
            navActionConsumer.offer(FromCatsList.ToSettings)
        }
    }
    private val appBarState = AnimatedAppBarState(
        titleState = AppBarTitleState(titleResId = R.string.cats_list_title),
        actionsState = listOf(settingsAction)
    )

    private var catsListRefreshJob: Job? = null

    val interactions = CatsListInteractions(
        onCatListItemSelected = {
            navActionConsumer.offer(FromCatsList.ToCatDetails(it.id))
        }
    )

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStartStop(onStart = ::onStart, onStop = ::onStop)
    }

    private fun onStart() {
        cleanUp()
        setUpTopBar()
        catsListRefreshJob = viewModelScope.launch {
            catsSource.observeCats().collect {
                _listState.value = CatsListState.Loaded(it)
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
        catsListRefreshJob?.cancel()
        catsListRefreshJob = null
    }
}
