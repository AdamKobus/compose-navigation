//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavigationStateSource](index.md)

# NavigationStateSource

[androidJvm]\
interface [NavigationStateSource](index.md)

Allows you to check the current state of back stack with [currentDestination](current-destination.md)

Allows you to observe back stack changes with [observeCurrentDestination](observe-current-destination.md)

## Properties

| Name | Summary |
|---|---|
| [currentDestination](current-destination.md) | [androidJvm]<br>abstract val [currentDestination](current-destination.md): [CurrentDestination](../../com.adamkobus.compose.navigation.destination/-current-destination/index.md)<br>Returns current back stack state synchronously |

## Functions

| Name | Summary |
|---|---|
| [observeCurrentDestination](observe-current-destination.md) | [androidJvm]<br>abstract fun [observeCurrentDestination](observe-current-destination.md)(): StateFlow&lt;[CurrentDestination](../../com.adamkobus.compose.navigation.destination/-current-destination/index.md)&gt;<br>Allows you to observe the changes to the back stack |
