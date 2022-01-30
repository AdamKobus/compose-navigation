package com.adamkobus.compose.navigation.demo.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.demo.nav.FromSplash
import com.adamkobus.compose.navigation.demo.ui.ext.onStart
import com.adamkobus.compose.navigation.demo.ui.topbar.TopBarStateSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@HiltViewModel
class SplashScreenVM @Inject constructor(
    private val navActionConsumer: NavActionConsumer,
    private val tobBarState: TopBarStateSource
) : ViewModel(), LifecycleEventObserver {

    init {
        viewModelScope.launch {
            delay(MOCK_DELAY_MS)
            navActionConsumer.offer(FromSplash.ToWelcome)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStart {
            viewModelScope.launch {
                tobBarState.setUpTopBar {}
            }
        }
    }

    companion object {
        private const val MOCK_DELAY_MS = 1000L
    }
}
