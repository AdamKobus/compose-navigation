//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavDestination](index.md)/[pop](pop.md)

# pop

[androidJvm]\
open fun [pop](pop.md)(): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)

Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that uses this [NavDestination](index.md) as a source destination and uses this destination's graph in target [PopDestination](../-pop-destination/index.md)

[androidJvm]\
open infix fun [pop](pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)

Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that originates from this [NavDestination](index.md) and targets [other](pop.md).

## Parameters

androidJvm

| | |
|---|---|
| other | will be used as target destination of created [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) |
