package com.adamkobus.compose.navigation.model

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.NavActionProcessor
import com.adamkobus.compose.navigation.data.NavAction
import javax.inject.Inject

internal class StubNavActionProcessor @Inject constructor() : NavActionProcessor {
    override fun process(action: NavAction, navController: NavHostController): Boolean = false
}
