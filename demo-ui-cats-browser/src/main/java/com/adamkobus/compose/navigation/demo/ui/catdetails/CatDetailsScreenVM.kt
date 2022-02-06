package com.adamkobus.compose.navigation.demo.ui.catdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarIconState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.cats.R
import com.adamkobus.compose.navigation.demo.ui.ext.onStartStop
import com.adamkobus.compose.navigation.demo.ui.nav.FromCatDetails
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
    private val navActionConsumer: NavActionConsumer,
    private val appBarStateSource: AppBarStateSource
) : ViewModel(), LifecycleEventObserver {

    private var catInfoRefreshJob: Job? = null
    private val catId = MutableStateFlow<Int?>(null)
    private val _screenState = mutableStateOf<CatDetailsState>(CatDetailsState.Loading)
    val screenState: State<CatDetailsState> = _screenState

    private val appBarState = AnimatedAppBarState(
        titleState = AppBarTitleState(titleResId = R.string.cat_details_title),
        iconState = AppBarIconState.back { onBackPressed() }
    )

    private fun onBackPressed() {
        navActionConsumer.offer(FromCatDetails.Back)
    }

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
        appBarStateSource.offer(appBarState)
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
