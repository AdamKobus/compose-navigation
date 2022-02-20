package com.adamkobus.compose.navigation.demo.ui.dogslist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarActionState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.dogs.R
import com.adamkobus.compose.navigation.demo.ui.nav.FromDogsList
import com.adamkobus.compose.navigation.democore.data.DogInfo
import com.adamkobus.compose.navigation.democore.model.DogsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsListScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource,
    private val dogsSource: DogsSource
) : LifecycleAwareViewModel() {

    private val isLoading = mutableStateOf(true)
    private val dogsList = mutableStateOf<List<DogInfo>>(emptyList())
    val screenState = DogsListScreenState(
        isLoading = isLoading,
        dogsList = dogsList
    )

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

    init {
        runOnStart {
            appBarStateSource.offer(appBarState)
            dogsSource.observeDogs().collect {
                isLoading.value = false
                dogsList.value = it
            }
        }
    }
}
