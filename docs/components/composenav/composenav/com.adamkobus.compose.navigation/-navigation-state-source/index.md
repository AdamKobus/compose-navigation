//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavigationStateSource](index.md)

# NavigationStateSource

[androidJvm]\
interface [NavigationStateSource](index.md)

Allows you to check the current state of back stack with [navState](nav-state.md)

Allows you to observe back stack changes with [observeNavState](observe-nav-state.md)

## Properties

| Name | Summary |
|---|---|
| [navState](nav-state.md) | [androidJvm]<br>abstract val [navState](nav-state.md): [NavState](../../com.adamkobus.compose.navigation.destination/-nav-state/index.md)<br>Returns current back stack state synchronously |

## Functions

| Name | Summary |
|---|---|
| [observeNavState](observe-nav-state.md) | [androidJvm]<br>abstract fun [observeNavState](observe-nav-state.md)(): StateFlow&lt;[NavState](../../com.adamkobus.compose.navigation.destination/-nav-state/index.md)&gt;<br>Allows you to observe the changes to the back stack |
