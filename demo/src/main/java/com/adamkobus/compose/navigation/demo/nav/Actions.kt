package com.adamkobus.compose.navigation.demo.nav

import com.adamkobus.compose.navigation.data.GlobalDestination
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.data.PopBackStackDestination
import com.adamkobus.compose.navigation.demo.settings.nav.SettingsGraph

sealed class FromSplash(action: NavAction) : NavAction(action) {
    object ToWelcome : FromSplash(
        AppGraph.SplashScreen to AppGraph.WelcomeScreen navigate {
            popUpTo(AppGraph.name)
            launchSingleTop = true
        }
    )
}

sealed class FromWelcome(action: NavAction) : NavAction(action) {
    object ToCatsList : FromWelcome(
        AppGraph.WelcomeScreen to AppGraph.CatsList navigate {
            popUpTo(AppGraph.name)
            launchSingleTop = true
        }
    )

    object ToDogsList : FromWelcome(
        AppGraph.WelcomeScreen to AppGraph.DogsList navigate {
            popUpTo(AppGraph.name)
            launchSingleTop = true
        }
    )
}

sealed class FromCatsList(action: NavAction) : NavAction(action) {
    // navigate is optional. It's needed only if you want to provide custom options to the NavOptionsBuilder
    // If you're fine with default navigation behaviour, then it's ok to skip "navigate" block
    class ToCatDetails(catId: Int) : FromCatsList(AppGraph.CatsList to AppGraph.CatDetails arg catId)
}

sealed class FromDogsList(action: NavAction) : NavAction(action) {
    class ToDogDetails(dogId: Int) : FromDogsList(AppGraph.DogsList to AppGraph.DogDetails arg dogId)
}

sealed class FromGlobal(action: NavAction) : NavAction(action) {
    object ToSettings : FromGlobal(GlobalDestination to SettingsGraph)

    object ToDemoDialog : FromGlobal(GlobalDestination to AppGraph.DemoDialog)
}

sealed class FromDemoDialog(action: NavAction) : NavAction(action) {
    object Dismiss : FromDemoDialog(NavAction(AppGraph.DemoDialog, PopBackStackDestination, navigateWithController = { it.popBackStack() }))
}
