package com.adamkobus.compose.navigation.demo.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.nav.FromSplash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@HiltViewModel
class SplashScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource
) : LifecycleAwareViewModel() {

    private val appBarState = AnimatedAppBarState()

    init {
        viewModelScope.launch {
            delay(MOCK_DELAY_MS)
            navigationConsumer.offer(FromSplash.ToWelcome)
        }

        runOnStart {
            appBarStateSource.offer(appBarState)
        }
    }

    companion object {
        private const val MOCK_DELAY_MS = 1000L
    }
}
