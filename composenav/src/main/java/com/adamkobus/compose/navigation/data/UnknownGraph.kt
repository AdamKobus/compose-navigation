package com.adamkobus.compose.navigation.data

import com.adamkobus.compose.navigation.destination.INavDestination

object UnknownGraph : NavGraph("__unknown__", reservedNameCheck = false) {
    override fun startDestination(): INavDestination = navDestination("__unknown__")
}
