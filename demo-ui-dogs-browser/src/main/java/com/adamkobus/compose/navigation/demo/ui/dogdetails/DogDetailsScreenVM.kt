package com.adamkobus.compose.navigation.demo.ui.dogdetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.android.vm.ViewParam
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarIconState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.dogs.R
import com.adamkobus.compose.navigation.demo.ui.nav.DogsBrowserGraph
import com.adamkobus.compose.navigation.demo.ui.nav.FromDogDetails
import com.adamkobus.compose.navigation.democore.data.DogInfo
import com.adamkobus.compose.navigation.democore.model.DogsSource
import com.adamkobus.compose.navigation.democore.util.AsyncData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailsScreenVM @Inject constructor(
    private val dogsSource: DogsSource,
    private val appBarStateSource: AppBarStateSource,
    private val navigationConsumer: NavigationConsumer
) : LifecycleAwareViewModel() {

    val dogIdParam = ViewParam<Int>()

    private val dogInfo = mutableStateOf<AsyncData<DogInfo, Throwable>>(AsyncData.Loading())
    val screenState = DogDetailsScreenState(
        dogInfo = dogInfo
    )

    val interactions = DogDetailsScreenInteractions(
        onOpenDialogClicked = {
            viewModelScope.launch {
                navigationConsumer.offer(FromDogDetails.ToDemoDialog)
            }
        },
        onOpenGalleryClicked = {
            viewModelScope.launch {
                val id = dogIdParam.observe().first()
                navigationConsumer.offer(FromDogDetails.ToGallery(id))
            }
        }
    )

    private val appBarState = AnimatedAppBarState(
        titleState = AppBarTitleState(titleResId = R.string.dog_details_title),
        iconState = AppBarIconState.back { onBackPressed() }
    )

    private fun onBackPressed() {
        navigationConsumer.offer(DogsBrowserGraph.DogDetails.pop())
    }

    init {
        runOnStart {
            appBarStateSource.offer(appBarState)
        }
        runOnStart {
            dogIdParam.observe().collect {
                try {
                    dogInfo.value = AsyncData.Present(dogsSource.getDog(it))
                } catch (e: IllegalArgumentException) {
                    dogInfo.value = AsyncData.Missing(e)
                }
            }
        }
    }
}
