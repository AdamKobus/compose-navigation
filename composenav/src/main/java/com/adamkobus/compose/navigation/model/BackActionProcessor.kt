package com.adamkobus.compose.navigation.model

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.NavActionProcessor
import com.adamkobus.compose.navigation.data.NavAction
import javax.inject.Inject

internal class BackActionProcessor @Inject constructor() : NavActionProcessor {
    override fun process(action: NavAction, navController: NavHostController): Boolean {
        if (action == NavAction.Back) {
            navController.popBackStack()
            return true
        }
        return false
    }
}
