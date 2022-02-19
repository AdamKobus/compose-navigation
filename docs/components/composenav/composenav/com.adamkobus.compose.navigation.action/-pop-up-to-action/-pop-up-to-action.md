//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[PopUpToAction](index.md)/[PopUpToAction](-pop-up-to-action.md)

# PopUpToAction

[androidJvm]\
fun [PopUpToAction](-pop-up-to-action.md)(fromDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), inclusive: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, saveState: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)

## Parameters

androidJvm

| | |
|---|---|
| toDestination | Destination to which the back stack will be popped |
| inclusive | if true then toDestination will be removed from back stack as well. |
| saveState | if true then popped destinations' state will be saved and it will be possible to restore it later by navigating to any of them with [NavOptions.restoreState](../-nav-options/restore-state.md) set to true |
