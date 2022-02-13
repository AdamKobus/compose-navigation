//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[PopOptions](index.md)

# PopOptions

[androidJvm]\
data class [PopOptions](index.md)

Used to define pop behaviour when navigating.

## Properties

| Name | Summary |
|---|---|
| [destination](destination.md) | [androidJvm]<br>val [destination](destination.md): [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md)<br>Destination to which the back stack should be popped before navigating. See androidx.navigation.PopUpToBuilder.popUpToRoute |
| [inclusive](inclusive.md) | [androidJvm]<br>val [inclusive](inclusive.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If set to true, then [destination](destination.md) will be included in the screens that are removed from back stack. See [androidx.navigation.PopUpToBuilder.inclusive](https://developer.android.com/reference/kotlin/androidx/navigation/PopUpToBuilder.html#inclusive) |
| [saveState](save-state.md) | [androidJvm]<br>val [saveState](save-state.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If set to true, then any screens that are removed from back stack will have their state saved beforehand. Their state can be later restored if [NavOptions.restoreState](../-nav-options/restore-state.md) flag is set to true when navigating back to them. See [androidx.navigation.PopUpToBuilder.saveState](https://developer.android.com/reference/kotlin/androidx/navigation/PopUpToBuilder.html#savestate) |
