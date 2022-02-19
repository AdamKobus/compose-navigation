package com.adamkobus.compose.navigation.demo.ui.doggallery

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
import com.adamkobus.compose.navigation.demo.ui.nav.FromDogsGallery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogGalleryScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val topBarState: AppBarStateSource
) : LifecycleAwareViewModel() {

    val dogId = ViewParam<Int>()

    private val appBarState = AnimatedAppBarState(
        titleState = AppBarTitleState(titleResId = R.string.dog_details_title),
        iconState = AppBarIconState.back { onBackPressed() }
    )

    val interactions = DogGalleryScreenInteractions(
        onBackToListClicked = {
            navigationConsumer.offer(FromDogsGallery.ToList)
        }
    )

    init {
        runOnStart {
            topBarState.offer(appBarState)
        }
    }

    private fun onBackPressed() {
        viewModelScope.launch {
            navigationConsumer.offer(DogsBrowserGraph.DogGallery.pop())
        }
    }
}
