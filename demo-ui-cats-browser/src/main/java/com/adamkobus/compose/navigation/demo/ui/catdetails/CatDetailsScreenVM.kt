package com.adamkobus.compose.navigation.demo.ui.catdetails

import androidx.compose.runtime.mutableStateOf
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.android.vm.ViewParam
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarIconState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.cats.R
import com.adamkobus.compose.navigation.demo.ui.nav.CatsBrowserGraph
import com.adamkobus.compose.navigation.democore.data.CatInfo
import com.adamkobus.compose.navigation.democore.model.CatsSource
import com.adamkobus.compose.navigation.democore.util.AsyncData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CatDetailsScreenVM @Inject constructor(
    private val catsSource: CatsSource,
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource,
) : LifecycleAwareViewModel() {
    val catIdParam = ViewParam<Int>()

    private val catInfo = mutableStateOf<AsyncData<CatInfo, Throwable>>(AsyncData.Loading())
    val screenState =
        CatDetailsScreenState(
            catInfo = catInfo,
        )

    private val appBarState =
        AnimatedAppBarState(
            titleState = AppBarTitleState(titleResId = R.string.cat_details_title),
            iconState = AppBarIconState.back { onBackPressed() },
        )

    private fun onBackPressed() {
        navigationConsumer.offer(CatsBrowserGraph.CatDetails.pop())
    }

    init {
        runOnStart {
            appBarStateSource.offer(appBarState)
        }
        runOnStart {
            catIdParam.observe().collect {
                try {
                    catInfo.value = AsyncData.Present(catsSource.getCat(it))
                } catch (e: IllegalArgumentException) {
                    catInfo.value = AsyncData.Missing(e)
                }
            }
        }
    }
}
