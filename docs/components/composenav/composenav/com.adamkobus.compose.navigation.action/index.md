//[composenav](../../index.md)/[com.adamkobus.compose.navigation.action](index.md)

# Package com.adamkobus.compose.navigation.action

## Types

| Name | Summary |
|---|---|
| [DiscardReason](-discard-reason/index.md) | [androidJvm]<br>sealed class [DiscardReason](-discard-reason/index.md)<br>Represents a reason for which [NavAction](-nav-action/index.md) processing was discarded. |
| [NavAction](-nav-action/index.md) | [androidJvm]<br>abstract class [NavAction](-nav-action/index.md)(fromDestination: [INavDestination](../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [INavDestination](../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md))<br>Base class for all navigation actions |
| [NavActionWrapper](-nav-action-wrapper/index.md) | [androidJvm]<br>open class [NavActionWrapper](-nav-action-wrapper/index.md)(action: [NavAction](-nav-action/index.md))<br>This can be used to build navigation actions using sealed class. The advantage of using [NavActionWrapper](-nav-action-wrapper/index.md) is that it's a structure that can be offered to [NavigationConsumer](../com.adamkobus.compose.navigation/-navigation-consumer/index.md). It also implements [equals](-nav-action-wrapper/equals.md) and [hashCode](-nav-action-wrapper/hash-code.md) methods, so that you don't have to do it in your sealed classes. |
| [NavigateAction](-navigate-action/index.md) | [androidJvm]<br>class [NavigateAction](-navigate-action/index.md)(fromNavDestination: [NavDestination](../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), toNavDestination: [NavDestination](../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, options: [NavOptions](-nav-options/index.md)?) : [NavAction](-nav-action/index.md)<br>Represents an action that changes current destination to toDestination |
| [NavigationResult](-navigation-result/index.md) | [androidJvm]<br>sealed class [NavigationResult](-navigation-result/index.md)<br>Used to indicate a result of [NavAction](-nav-action/index.md) or [NavIntent](../com.adamkobus.compose.navigation.intent/-nav-intent/index.md) processing. |
| [NavOptions](-nav-options/index.md) | [androidJvm]<br>data class [NavOptions](-nav-options/index.md)<br>Represents options that can be used when navigating to a new destination. Use [navActionOptions](nav-action-options.md) to create new instance of [NavOptions](-nav-options/index.md) |
| [NavOptionsBuilder](-nav-options-builder/index.md) | [androidJvm]<br>data class [NavOptionsBuilder](-nav-options-builder/index.md)<br>Used to construct [NavOptions](-nav-options/index.md). It's similar to Android's [androidx.navigation.NavOptionsBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html). The only difference is custom [PopOptionsBuilder](-pop-options-builder/index.md) |
| [PopAction](-pop-action/index.md) | [androidJvm]<br>class [PopAction](-pop-action/index.md)(fromDestination: [NavDestination](../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), toDestination: [PopDestination](../com.adamkobus.compose.navigation.destination/-pop-destination/index.md)) : [NavAction](-nav-action/index.md)<br>This action results in [NavHostController.popBackStack](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html#popbackstack) being called |
| [PopOptions](-pop-options/index.md) | [androidJvm]<br>data class [PopOptions](-pop-options/index.md)<br>Used to define pop behaviour when navigating. |
| [PopOptionsBuilder](-pop-options-builder/index.md) | [androidJvm]<br>data class [PopOptionsBuilder](-pop-options-builder/index.md)<br>Builder for [PopOptions](-pop-options/index.md) |

## Functions

| Name | Summary |
|---|---|
| [navActionOptions](nav-action-options.md) | [androidJvm]<br>fun [navActionOptions](nav-action-options.md)(init: [NavOptionsBuilder](-nav-options-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavOptions](-nav-options/index.md)<br>Creates a set of options to be used when navigating to new destination. |
