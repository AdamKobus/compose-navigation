package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.action.navActionOptions

sealed class FromSplash(action: NavAction) : NavActionWrapper(action) {
    object ToWelcome : FromSplash(
        AppGraph.SplashScreen goTo OnBoardingGraph withOptions navActionOptions {
            popUpTo(AppRootGraph)
            launchSingleTop = true
        }
    )
}

sealed class FromWelcome(action: NavAction) : NavActionWrapper(action) {
    object ToCatsList : FromWelcome(
        OnBoardingGraph.WelcomeScreen.goTo(CatsBrowserGraph)
            .setNavOptions {
                popUpTo(AppRootGraph)
                launchSingleTop = true
            }
    )

    object ToDogsList : FromWelcome(
        OnBoardingGraph.WelcomeScreen goTo DogsBrowserGraph withOptions navActionOptions {
            popUpTo(AppRootGraph)
            launchSingleTop = true
        }
    )
}

sealed class FromCatsList(action: NavAction) : NavActionWrapper(action) {
    class ToCatDetails(catId: Int) : FromCatsList(
        CatsBrowserGraph.CatsList goTo CatsBrowserGraph.CatDetails arg catId withOptions navActionOptions {
            popUpTo(CatsBrowserGraph.CatsList)
        }
    )

    object ToSettings : FromCatsList(CatsBrowserGraph.CatsList goTo SettingsGraph)
}

sealed class FromCatDetails(action: NavAction) : NavActionWrapper(action) {
    object Back : FromCatDetails(CatsBrowserGraph.CatDetails.pop())
}

sealed class FromDogsList(action: NavAction) : NavActionWrapper(action) {
    class ToDogDetails(dogId: Int) : FromDogsList(DogsBrowserGraph.DogsList goTo DogsBrowserGraph.DogDetails arg dogId)
    object ToSettings : FromDogsList(DogsBrowserGraph.DogsList goTo SettingsGraph)
}

sealed class FromDogDetails(action: NavAction) : NavActionWrapper(action) {
    object Back : FromDogDetails(DogsBrowserGraph.DogDetails.pop())
    object ToDemoDialog : FromDogDetails(DogsBrowserGraph.DogDetails goTo DogsBrowserGraph.DemoDialog)
    class ToGallery(dogId: Int) : FromDogDetails(DogsBrowserGraph.DogDetails goTo DogsBrowserGraph.DogGallery arg dogId)
}

sealed class FromDemoDialog(action: NavAction) : NavActionWrapper(action) {
    object Dismiss : FromDemoDialog(DogsBrowserGraph.DemoDialog.pop())
}

sealed class FromSettingsHome(action: NavAction) : NavActionWrapper(action) {
    object Back : FromSettingsHome(SettingsGraph.SettingsHome.pop())
}

sealed class FromDogsGallery(action: NavAction) : NavActionWrapper(action) {
    object ToList : FromDogsGallery(DogsBrowserGraph.DogGallery.popUpTo(DogsBrowserGraph.DogsList))
}
