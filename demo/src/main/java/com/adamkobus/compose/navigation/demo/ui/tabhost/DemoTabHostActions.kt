package com.adamkobus.compose.navigation.demo.ui.tabhost

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.demo.nav.PetsGraph
import com.adamkobus.compose.navigation.demo.ui.nav.CatsBrowserGraph
import com.adamkobus.compose.navigation.demo.ui.nav.DogsBrowserGraph

sealed class DemoTabHostActions(action: NavAction) : NavActionWrapper(action) {

    // TODO replace all of this will NavIntent. NavIntent will resolve proper action based on current stack
    object ToCatsRoot : NavActionWrapper(
        GlobalGraph to CatsBrowserGraph navigate {
            popUpTo(PetsGraph.name)
        }
    )

    object FromGlobalToCats : NavActionWrapper(
        GlobalGraph to CatsBrowserGraph navigate {
            popUpTo(PetsGraph.name) {
                saveState = true
            }
            restoreState = true
        }
    )

    object ToDogsRoot : NavActionWrapper(
        GlobalGraph to DogsBrowserGraph navigate {
            popUpTo(PetsGraph.name)
        }
    )

    object FromGlobalToDogs : NavActionWrapper(
        GlobalGraph to DogsBrowserGraph navigate {
            popUpTo(PetsGraph.name) {
                saveState = true
            }
            restoreState = true
        }
    )
}
