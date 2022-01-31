package com.adamkobus.compose.navigation.demo.ui.welcome

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.demo.nav.FromWelcome
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.ext.onStart
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenVM @Inject constructor(
    private val navActionConsumer: NavActionConsumer,
    private val appBarStateSource: AppBarStateSource
) : ViewModel(), LifecycleEventObserver {

    val interactions = WelcomeScreenInteractions(
        onDogsSelected = {
            navActionConsumer.offer(FromWelcome.ToDogsList)
        },
        onCatsSelected = {
            navActionConsumer.offer(FromWelcome.ToCatsList)
        }
    )

    private val appBatState = AnimatedAppBarState()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStart {
            appBarStateSource.offer(appBatState)
        }
    }
}
