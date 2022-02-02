package com.adamkobus.compose.navigation.demo.nav

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.data.INavDestination
import com.adamkobus.compose.navigation.data.NavAction
import javax.inject.Inject

class AppNavActionVerifier @Inject constructor() : NavActionVerifier {
    override fun isNavActionAllowed(currentDestination: INavDestination, action: NavAction): Boolean {
        return when (currentDestination) {
            // prevents opening cat / dog details screen in case user clicked 2 list items.
            AppGraph.CatDetails -> action.toDestination != AppGraph.CatDetails
            AppGraph.DogDetails -> action.toDestination != AppGraph.DogDetails
            else -> true
        }
        // You could also block all potential duplicates with conditions like below
        // return currentDestination != action.toDestination
        // return currentDestination == action.fromDestination
        // Although special cases like Global actions would have to be taken into account.
    }
}
