package com.adamkobus.compose.navigation.data

val GlobalGraph = object : NavGraph {
    override val name: String = "global"
}

val GlobalDestination = navDestination(GlobalGraph, "any")

val PopBackStackDestination = navDestination(GlobalGraph, "back")
