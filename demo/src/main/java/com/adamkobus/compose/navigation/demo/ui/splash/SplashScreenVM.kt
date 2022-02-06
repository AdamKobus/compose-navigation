package com.adamkobus.compose.navigation.demo.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.ext.onStart
import com.adamkobus.compose.navigation.demo.ui.nav.FromSplash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@HiltViewModel
class SplashScreenVM @Inject constructor(
    private val navActionConsumer: NavActionConsumer,
    private val appBarStateSource: AppBarStateSource
) : ViewModel(), LifecycleEventObserver {

    private val appBarState = AnimatedAppBarState()

    init {
        viewModelScope.launch {
            delay(MOCK_DELAY_MS)
            navActionConsumer.offer(FromSplash.ToWelcome)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStart {
            viewModelScope.launch {
                appBarStateSource.offer(appBarState)
            }
        }
    }

    companion object {
        private const val MOCK_DELAY_MS = 1000L
    }
}
