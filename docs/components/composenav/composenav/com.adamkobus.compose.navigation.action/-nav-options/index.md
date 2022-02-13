//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavOptions](index.md)

# NavOptions

[androidJvm]\
data class [NavOptions](index.md)

Represents options that can be used when navigating to a new destination. Use [navActionOptions](../nav-action-options.md) to create new instance of [NavOptions](index.md)

## Properties

| Name | Summary |
|---|---|
| [launchSingleTop](launch-single-top.md) | [androidJvm]<br>val [launchSingleTop](launch-single-top.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If set to true, then new screen won't be added to back stack if the target destination is already preset in it. See [androidx.navigation.NavOptionsBuilder.launchSingleTop](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html#launchsingletop) |
| [popOptions](pop-options.md) | [androidJvm]<br>val [popOptions](pop-options.md): [PopOptions](../-pop-options/index.md)?<br>If not null, then back stack will be popped before navigating to a new screen. [PopOptions](../-pop-options/index.md) are used to configure the pop behaviour. |
| [restoreState](restore-state.md) | [androidJvm]<br>val [restoreState](restore-state.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If set to true, then the state of the new screen will be restored given that it was previously saved when removing it from back stack. See [androidx.navigation.NavOptionsBuilder.restoreState](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html#restorestate) |

## Functions

| Name | Summary |
|---|---|
| [toAndroidNavOptions](to-android-nav-options.md) | [androidJvm]<br>fun [toAndroidNavOptions](to-android-nav-options.md)(): [NavOptions](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptions.html)<br>[NavOptions](index.md) were introduced for internal use by Compose Navigation library. This function converts them into [androidx.navigation.NavOptions](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptions.html). |
