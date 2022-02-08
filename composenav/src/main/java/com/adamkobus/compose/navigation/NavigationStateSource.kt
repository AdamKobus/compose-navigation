package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.destination.INavDestination
import kotlinx.coroutines.flow.Flow

interface NavigationStateSource {
    val currentDestination: INavDestination?

    fun observeCurrentDestination(): Flow<INavDestination?>
}
