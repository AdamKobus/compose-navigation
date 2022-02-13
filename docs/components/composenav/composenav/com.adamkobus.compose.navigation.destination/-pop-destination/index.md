//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[PopDestination](index.md)

# PopDestination

[androidJvm]\
data class [PopDestination](index.md)(graph: [NavGraph](../-nav-graph/index.md), route: [NavRoute](../-nav-route/index.md)) : [INavDestination](../-i-nav-destination/index.md)

This is a special kind of destination that can be used only as a target of [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)

## Constructors

| | |
|---|---|
| [PopDestination](-pop-destination.md) | [androidJvm]<br>fun [PopDestination](-pop-destination.md)(graph: [NavGraph](../-nav-graph/index.md), route: [NavRoute](../-nav-route/index.md) = navRoute(graph.name, path = "__back__", reservedNamesCheck = false)) |

## Properties

| Name | Summary |
|---|---|
| [graph](graph.md) | [androidJvm]<br>open override val [graph](graph.md): [NavGraph](../-nav-graph/index.md)<br>Graph that this destination belongs to |
| [route](route.md) | [androidJvm]<br>open override val [route](route.md): [NavRoute](../-nav-route/index.md)<br>Each destination must have a unique route. |

## Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
