package com.adamkobus.compose.navigation.action

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.PopDestination

class PopAction(
    fromDestination: INavDestination,
    toDestination: PopDestination = GlobalGraph.Back,
    private val navigate: (NavOptionsBuilder.() -> Unit)? = null
) : NavAction(fromDestination = fromDestination, toDestination = toDestination) {

    infix fun navigate(param: NavOptionsBuilder.() -> Unit): PopAction =
        PopAction(fromDestination, navigate = param)

    override fun equals(other: Any?): Boolean {
        return other is PopAction &&
            super.equals(other) &&
            other.fromDestination == fromDestination &&
            other.toDestination == toDestination &&
            other.navigate == navigate
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (navigate?.hashCode() ?: 0)
        return result
    }

    override fun navigate(controller: NavHostController) {
        controller.popBackStack()
    }
}
