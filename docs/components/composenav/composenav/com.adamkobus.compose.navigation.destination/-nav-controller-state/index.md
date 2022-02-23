//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavControllerState](index.md)

# NavControllerState

[androidJvm]\
data class [NavControllerState](index.md)(navId: [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md), currentDestination: [NavStackEntry](../-nav-stack-entry/index.md)?, backStack: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavStackEntry](../-nav-stack-entry/index.md)&gt;)

Represents the current state of the back stack in controller identified by [navId](nav-id.md)

## Parameters

androidJvm

| | |
|---|---|
| navId | [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md) of controller that is responsible for handling represented back stack. |
| currentDestination | the destination that is currently displayed to the user. |
| backStack | all of the destinations that are currently in the back stack, including graphs. |

## Constructors

| | |
|---|---|
| [NavControllerState](-nav-controller-state.md) | [androidJvm]<br>fun [NavControllerState](-nav-controller-state.md)(navId: [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md), currentDestination: [NavStackEntry](../-nav-stack-entry/index.md)?, backStack: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavStackEntry](../-nav-stack-entry/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [backStack](back-stack.md) | [androidJvm]<br>val [backStack](back-stack.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavStackEntry](../-nav-stack-entry/index.md)&gt; |
| [currentDestination](current-destination.md) | [androidJvm]<br>val [currentDestination](current-destination.md): [NavStackEntry](../-nav-stack-entry/index.md)? |
| [navId](nav-id.md) | [androidJvm]<br>val [navId](nav-id.md): [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md) |

## Functions

| Name | Summary |
|---|---|
| [isCurrent](is-current.md) | [androidJvm]<br>fun [isCurrent](is-current.md)(destination: [INavDestination](../-i-nav-destination/index.md)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if [destination](is-current.md) is currently visible to the user |
| [isInBackStack](is-in-back-stack.md) | [androidJvm]<br>fun [isInBackStack](is-in-back-stack.md)(dest: [INavDestination](../-i-nav-destination/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if provided destination is currently in back stack |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a String representation of [NavControllerState](index.md) |
