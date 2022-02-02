package com.adamkobus.compose.navigation.data

object GlobalGraph : NavGraph {
    override val name: String = "__global__"
}

val GlobalDestination = navDestination(GlobalGraph, "any")

val PopBackStackDestination = navDestination(GlobalGraph, "back")
