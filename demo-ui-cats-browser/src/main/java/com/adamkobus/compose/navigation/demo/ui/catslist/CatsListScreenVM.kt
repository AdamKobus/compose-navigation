package com.adamkobus.compose.navigation.demo.ui.catslist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarActionState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.cats.R
import com.adamkobus.compose.navigation.demo.ui.nav.FromCatsList
import com.adamkobus.compose.navigation.democore.data.CatInfo
import com.adamkobus.compose.navigation.democore.model.CatsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsListScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource,
    private val catsSource: CatsSource,
) : LifecycleAwareViewModel() {
    private val isLoading = mutableStateOf(true)
    private val catsList = mutableStateOf<List<CatInfo>>(emptyList())
    val screenState =
        CatsListScreenState(
            isLoading = isLoading,
            catsList = catsList,
        )

    val interactions =
        CatsListInteractions(
            onCatListItemSelected = {
                navigationConsumer.offer(FromCatsList.ToCatDetails(it.id))
            },
        )

    private val settingsAction =
        AppBarActionState.settings {
            viewModelScope.launch {
                navigationConsumer.offer(FromCatsList.ToSettings)
            }
        }
    private val appBarState =
        AnimatedAppBarState(
            titleState = AppBarTitleState(titleResId = R.string.cats_list_title),
            actionsState = listOf(settingsAction),
        )

    init {
        runOnStart {
            appBarStateSource.offer(appBarState)
            catsSource.observeCats().collect {
                isLoading.value = false
                catsList.value = it
            }
        }
    }
}
