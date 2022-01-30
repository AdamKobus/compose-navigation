package com.adamkobus.compose.navigation.demo.ui.catdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.demo.R
import com.adamkobus.compose.navigation.demo.ui.Elevation
import com.adamkobus.compose.navigation.demo.ui.ext.onStartStop
import com.adamkobus.compose.navigation.demo.ui.topbar.DemoColors
import com.adamkobus.compose.navigation.demo.ui.topbar.TopBarStateSource
import com.adamkobus.compose.navigation.demo.ui.topbar.backButton
import com.adamkobus.compose.navigation.democore.data.CatInfo
import com.adamkobus.compose.navigation.democore.model.CatsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailsScreenVM @Inject constructor(
    private val catsSource: CatsSource,
    private val topBarState: TopBarStateSource,
    private val navActionConsumer: NavActionConsumer,
) : ViewModel(), LifecycleEventObserver {

    private var catInfoRefreshJob: Job? = null
    private val catId = MutableStateFlow<Int?>(null)
    private val _screenState = mutableStateOf<CatDetailsState>(CatDetailsState.Loading)
    val screenState: State<CatDetailsState> = _screenState

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStartStop(onStart = ::onStart, onStop = ::onStop)
    }

    fun bindCatId(id: Int) {
        catId.value = id
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun onStart() {
        cleanUp()
        setUpTopBar()
        catInfoRefreshJob = viewModelScope.launch {
            catId.collect { id ->
                try {
                    onCatIdUpdated(id)
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                    _screenState.value = CatDetailsState.Error
                }
            }
        }
    }

    private fun setUpTopBar() {
        topBarState.setUpTopBar {
            titleResId = R.string.cat_details_title
            background = DemoColors.PrimarySurface
            elevation = Elevation.AppBar
            backButton { onBackPressed() }
        }
    }

    private fun onBackPressed() {
        navActionConsumer.offer(NavAction.Back)
    }

    private suspend fun onCatIdUpdated(id: Int?) {
        if (id != null) {
            val catInfo = catsSource.getCat(id)
            onCatInfoLoaded(catInfo)
        } else {
            _screenState.value = CatDetailsState.Loading
        }
    }

    private fun onCatInfoLoaded(catInfo: CatInfo?) {
        if (catInfo != null) {
            _screenState.value = CatDetailsState.Loaded(catInfo = catInfo)
        } else {
            _screenState.value = CatDetailsState.Error
        }
    }

    private fun onStop() {
        cleanUp()
    }

    private fun cleanUp() {
        catInfoRefreshJob?.cancel()
        catInfoRefreshJob = null
    }
}
