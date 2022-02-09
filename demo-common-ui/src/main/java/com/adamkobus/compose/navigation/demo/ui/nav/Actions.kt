package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper

sealed class FromSplash(action: NavAction) : NavActionWrapper(action) {
    object ToWelcome : FromSplash(
        AppGraph.SplashScreen goTo OnBoardingGraph navigate {
            popUpTo(AppGraph.name)
            launchSingleTop = true
        }
    )
}

sealed class FromWelcome(action: NavAction) : NavActionWrapper(action) {
    object ToCatsList : FromWelcome(
        OnBoardingGraph.WelcomeScreen goTo CatsBrowserGraph navigate {
            popUpTo(OnBoardingGraph.name) {
                inclusive = true
            }
            launchSingleTop = true
        }
    )

    object ToDogsList : FromWelcome(
        OnBoardingGraph.WelcomeScreen goTo DogsBrowserGraph navigate {
            popUpTo(OnBoardingGraph.name) {
                inclusive = true
            }
            launchSingleTop = true
        }
    )
}

sealed class FromCatsList(action: NavAction) : NavActionWrapper(action) {
    class ToCatDetails(catId: Int) : FromCatsList(
        CatsBrowserGraph.CatsList goTo CatsBrowserGraph.CatDetails arg catId navigate {
            popUpTo(CatsBrowserGraph.CatsList.route.buildRoute())
        }
    )

    object ToSettings : FromCatsList(CatsBrowserGraph.CatsList goTo SettingsGraph)
}

sealed class FromCatDetails(action: NavAction) : NavActionWrapper(action) {
    object Back : FromCatDetails(CatsBrowserGraph.CatDetails pop CatsBrowserGraph.Back)
}

sealed class FromDogsList(action: NavAction) : NavActionWrapper(action) {
    class ToDogDetails(dogId: Int) : FromDogsList(DogsBrowserGraph.DogsList goTo DogsBrowserGraph.DogDetails arg dogId)
    object ToSettings : FromDogsList(DogsBrowserGraph.DogsList goTo SettingsGraph)
}

sealed class FromDogDetails(action: NavAction) : NavActionWrapper(action) {
    object Back : FromDogDetails(DogsBrowserGraph.DogDetails pop DogsBrowserGraph.Back)
    object ToDemoDialog : FromDogDetails(DogsBrowserGraph.DogDetails goTo DogsBrowserGraph.DemoDialog)
}

sealed class FromDemoDialog(action: NavAction) : NavActionWrapper(action) {
    object Dismiss : FromDemoDialog(DogsBrowserGraph.DemoDialog pop DogsBrowserGraph.Back)
}

sealed class FromSettingsHome(action: NavAction) : NavActionWrapper(action) {
    object Back : FromSettingsHome(SettingsGraph.SettingsHome pop SettingsGraph.Back)
}
