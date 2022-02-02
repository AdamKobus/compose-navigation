package com.adamkobus.compose.navigation.demo.nav

import com.adamkobus.compose.navigation.data.GlobalDestination
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.data.NavActionWrapper
import com.adamkobus.compose.navigation.demo.settings.nav.SettingsGraph

sealed class FromSplash(override val action: NavAction) : NavActionWrapper {
    object ToWelcome : FromSplash(AppGraph.SplashScreen to AppGraph.WelcomeScreen)
}

sealed class FromWelcome(override val action: NavAction) : NavActionWrapper {
    object ToCatsList : FromWelcome(AppGraph.WelcomeScreen to AppGraph.CatsList)
    object ToDogsList : FromWelcome(AppGraph.WelcomeScreen to AppGraph.DogsList)
}

sealed class FromCatsList(override val action: NavAction) : NavActionWrapper {
    class ToCatDetails(catId: Int) : FromCatsList(AppGraph.CatsList to AppGraph.CatDetails arg catId)
}

sealed class FromDogsList(override val action: NavAction) : NavActionWrapper {
    class ToDogDetails(dogId: Int) : FromDogsList(AppGraph.DogsList to AppGraph.DogDetails arg dogId)
}

sealed class FromGlobal(override val action: NavAction) : NavActionWrapper {
    object ToSettings : FromGlobal(GlobalDestination to SettingsGraph)
}
