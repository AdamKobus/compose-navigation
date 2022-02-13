//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavOptionsBuilder](index.md)

# NavOptionsBuilder

[androidJvm]\
data class [NavOptionsBuilder](index.md)

Used to construct [NavOptions](../-nav-options/index.md). It's similar to Android's [androidx.navigation.NavOptionsBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html). The only difference is custom [PopOptionsBuilder](../-pop-options-builder/index.md)

## Properties

| Name | Summary |
|---|---|
| [launchSingleTop](launch-single-top.md) | [androidJvm]<br>var [launchSingleTop](launch-single-top.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>see [androidx.navigation.NavOptionsBuilder.launchSingleTop](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html#launchsingletop) |
| [restoreState](restore-state.md) | [androidJvm]<br>var [restoreState](restore-state.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>see [androidx.navigation.NavOptionsBuilder.restoreState](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html#restorestate) |

## Functions

| Name | Summary |
|---|---|
| [anim](anim.md) | [androidJvm]<br>fun [anim](anim.md)(init: [AnimBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/AnimBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Provides a way to set up custom animation options |
| [clearAnim](clear-anim.md) | [androidJvm]<br>fun [clearAnim](clear-anim.md)()<br>Clears [AnimBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/AnimBuilder.html) that was previously set with [NavOptionsBuilder.anim](anim.md) |
| [clearPop](clear-pop.md) | [androidJvm]<br>fun [clearPop](clear-pop.md)()<br>Clears [PopOptions](../-pop-options/index.md) that were previously set with [NavOptionsBuilder.popUpTo](pop-up-to.md) |
| [popUpTo](pop-up-to.md) | [androidJvm]<br>fun [popUpTo](pop-up-to.md)(destination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md))<br>fun [popUpTo](pop-up-to.md)(destination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), init: [PopOptionsBuilder](../-pop-options-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Creates [PopOptions](../-pop-options/index.md) |
