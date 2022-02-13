//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavGraph](index.md)/[startDestination](start-destination.md)

# startDestination

[androidJvm]\
abstract fun [startDestination](start-destination.md)(): [NavDestination](../-nav-destination/index.md)

Each graph must have a start destination which NavHostController will try to display when you navigate to this graph. It's ok to have another graph as start destination, but eventually one of the graphs should return [ScreenDestination](../-screen-destination/index.md).
