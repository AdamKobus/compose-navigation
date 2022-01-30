package com.adamkobus.compose.navigation.demo.nav

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.data.INavDestination
import com.adamkobus.compose.navigation.data.NavAction
import javax.inject.Inject

class AppNavActionVerifier @Inject constructor() : NavActionVerifier {
    override fun isNavActionAllowed(currentDestination: INavDestination, action: NavAction): Boolean {
        return when (currentDestination) {
            Destinations.CatDetails -> action.toDestination != Destinations.CatDetails
            Destinations.DogDetails -> action.toDestination != Destinations.DogDetails
            // otherwise allow all
            // you could also do a global check like below, but I'm not sure if there are any edge cases related to this yet
            // return currentDestination != action.toDestination
            else -> true
        }
    }
}
