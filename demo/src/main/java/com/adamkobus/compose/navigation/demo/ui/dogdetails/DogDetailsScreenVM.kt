package com.adamkobus.compose.navigation.demo.ui.dogdetails

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
import com.adamkobus.compose.navigation.demo.nav.FromGlobal
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarIconState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.ext.onStartStop
import com.adamkobus.compose.navigation.democore.data.DogInfo
import com.adamkobus.compose.navigation.democore.model.DogsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailsScreenVM @Inject constructor(
    private val dogsSource: DogsSource,
    private val topBarState: AppBarStateSource,
    private val navActionConsumer: NavActionConsumer
) : ViewModel(), LifecycleEventObserver {

    private var dogInfoRefreshJob: Job? = null
    private val dogId = MutableStateFlow<Int?>(null)
    private val _screenState = mutableStateOf<DogDetailsState>(DogDetailsState.Loading)
    val screenState: State<DogDetailsState> = _screenState

    private val appBarState = AnimatedAppBarState(
        titleState = AppBarTitleState(titleResId = R.string.dog_details_title),
        iconState = AppBarIconState.back { onBackPressed() }
    )

    val interactions = DogDetailsScreenInteractions(
        onOpenDialogClicked = {
            viewModelScope.launch {
                navActionConsumer.offer(FromGlobal.ToDemoDialog)
            }
        }
    )

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStartStop(onStart = ::onStart, onStop = ::onStop)
    }

    fun bindDogId(id: Int) {
        dogId.value = id
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun onStart() {
        cleanUp()
        setUpTopBar()
        dogInfoRefreshJob = viewModelScope.launch {
            dogId.collect { id ->
                try {
                    onDogIdUpdated(id)
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                    _screenState.value = DogDetailsState.Error
                }
            }
        }
    }

    private fun setUpTopBar() {
        topBarState.offer(appBarState)
    }

    private fun onBackPressed() {
        navActionConsumer.offer(NavAction.Back)
    }

    private suspend fun onDogIdUpdated(id: Int?) {
        if (id != null) {
            val dogInfo = dogsSource.getDog(id)
            onDogInfoLoaded(dogInfo)
        } else {
            _screenState.value = DogDetailsState.Loading
        }
    }

    private fun onDogInfoLoaded(dogInfo: DogInfo?) {
        if (dogInfo != null) {
            _screenState.value = DogDetailsState.Loaded(dogInfo = dogInfo)
        } else {
            _screenState.value = DogDetailsState.Error
        }
    }

    private fun onStop() {
        cleanUp()
    }

    private fun cleanUp() {
        dogInfoRefreshJob?.cancel()
        dogInfoRefreshJob = null
    }
}
