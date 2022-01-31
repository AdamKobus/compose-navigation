package com.adamkobus.compose.navigation.demo.nav

import com.adamkobus.compose.navigation.data.GlobalDestination
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.data.NavActionWrapper

sealed class FromSplash(override val action: NavAction) : NavActionWrapper {
    object ToWelcome : FromSplash(Destinations.SplashScreen to Destinations.WelcomeScreen)
}

sealed class FromWelcome(override val action: NavAction) : NavActionWrapper {
    object ToCatsList : FromWelcome(Destinations.WelcomeScreen to Destinations.CatsList)
    object ToDogsList : FromWelcome(Destinations.WelcomeScreen to Destinations.DogsList)
}

sealed class FromCatsList(override val action: NavAction) : NavActionWrapper {
    class ToCatDetails(catId: Int) : FromCatsList(Destinations.CatsList to Destinations.CatDetails arg catId)
}

sealed class FromDogsList(override val action: NavAction) : NavActionWrapper {
    class ToDogDetails(dogId: Int) : FromDogsList(Destinations.DogsList to Destinations.DogDetails arg dogId)
}

sealed class FromGlobal(override val action: NavAction) : NavActionWrapper {
    object ToSettings : FromGlobal(GlobalDestination to Destinations.Settings)
}
