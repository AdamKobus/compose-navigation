package com.adamkobus.compose.navigation.data

val GlobalGraph = object : NavGraph {
    override val name: String = "global"
}

val GlobalDestination = NavDestination(GlobalGraph, "any")

val PopBackStackDestination = NavDestination(GlobalGraph, "back")
