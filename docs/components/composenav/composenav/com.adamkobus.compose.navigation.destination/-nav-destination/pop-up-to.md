//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavDestination](index.md)/[popUpTo](pop-up-to.md)

# popUpTo

[androidJvm]\
open fun [popUpTo](pop-up-to.md)(destination: [NavDestination](index.md), inclusive: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, saveState: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [PopUpToAction](../../com.adamkobus.compose.navigation.action/-pop-up-to-action/index.md)

Creates [PopUpToAction](../../com.adamkobus.compose.navigation.action/-pop-up-to-action/index.md)

## Parameters

androidJvm

| | |
|---|---|
| destination | Destination to which the back stack will be popped |
| inclusive | if true then [destination](pop-up-to.md) will be removed from back stack as well. |
| saveState | if true then popped destinations' state will be saved and it will be possible to restore it later by navigating to any of them with [NavOptions.restoreState](../../com.adamkobus.compose.navigation.action/-nav-options/restore-state.md) set to true |
