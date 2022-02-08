package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.destination.CurrentDestination
import kotlinx.coroutines.flow.Flow

interface NavigationStateSource {
    val currentDestination: CurrentDestination

    fun observeCurrentDestination(): Flow<CurrentDestination>
}
