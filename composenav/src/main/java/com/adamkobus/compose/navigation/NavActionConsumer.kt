package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.data.NavActionWrapper

interface NavActionConsumer {
    fun offer(action: NavAction)
    fun offer(action: NavActionWrapper)
}
