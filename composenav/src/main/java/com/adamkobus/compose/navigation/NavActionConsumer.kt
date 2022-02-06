package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper

interface NavActionConsumer {
    fun offer(action: NavAction)
    fun offer(action: NavActionWrapper)
}
