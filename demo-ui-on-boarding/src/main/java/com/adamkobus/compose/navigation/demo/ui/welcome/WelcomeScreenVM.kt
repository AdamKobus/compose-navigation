package com.adamkobus.compose.navigation.demo.ui.welcome

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.ext.onStart
import com.adamkobus.compose.navigation.demo.ui.nav.FromWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource
) : ViewModel(), LifecycleEventObserver {

    val interactions = WelcomeScreenInteractions(
        onDogsSelected = {
            navigationConsumer.offer(FromWelcome.ToDogsList)
        },
        onCatsSelected = {
            navigationConsumer.offer(FromWelcome.ToCatsList)
        }
    )

    private val appBatState = AnimatedAppBarState()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStart {
            appBarStateSource.offer(appBatState)
        }
    }
}
