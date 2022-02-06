package com.adamkobus.compose.navigation.demo.nav

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.demo.settings.nav.SettingsGraph

sealed class FromSplash(action: NavAction) : NavActionWrapper(action) {
    object ToWelcome : FromSplash(
        AppGraph.SplashScreen to AppGraph.WelcomeScreen navigate {
            popUpTo(AppGraph.name)
            launchSingleTop = true
        }
    )
}

sealed class FromWelcome(action: NavAction) : NavActionWrapper(action) {
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

sealed class FromCatsList(action: NavAction) : NavActionWrapper(action) {
    // navigate is optional. It's needed only if you want to provide custom options to the NavOptionsBuilder
    // If you're fine with default navigation behaviour, then it's ok to skip "navigate" block
    class ToCatDetails(catId: Int) : FromCatsList(AppGraph.CatsList to AppGraph.CatDetails arg catId)
}

sealed class FromCatDetails(action: NavAction) : NavActionWrapper(action) {
    object Back : FromCatDetails(AppGraph.CatDetails to AppGraph.Back)
}

sealed class FromDogsList(action: NavAction) : NavActionWrapper(action) {
    class ToDogDetails(dogId: Int) : FromDogsList(AppGraph.DogsList to AppGraph.DogDetails arg dogId)
}

sealed class FromDogDetails(action: NavAction) : NavActionWrapper(action) {
    object Back : FromDogDetails(AppGraph.DogDetails to AppGraph.Back)
}

sealed class FromGlobal(action: NavAction) : NavActionWrapper(action) {
    object ToSettings : FromGlobal(GlobalGraph to SettingsGraph)

    object ToDemoDialog : FromGlobal(GlobalGraph to AppGraph.DemoDialog)
}

sealed class FromDemoDialog(action: NavAction) : NavActionWrapper(action) {
    object Dismiss : FromDemoDialog(AppGraph.DemoDialog to AppGraph.Back)
}
