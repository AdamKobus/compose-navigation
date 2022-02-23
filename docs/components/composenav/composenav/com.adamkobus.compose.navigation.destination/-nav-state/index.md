//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavState](index.md)

# NavState

[androidJvm]\
data class [NavState](index.md)(controllersState: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavControllerState](../-nav-controller-state/index.md)&gt;)

Represents the current state of the back stack in all of the tracked controllers.

## Parameters

androidJvm

| | |
|---|---|
| controllersState | Each [NavControllerState](../-nav-controller-state/index.md) represents the back stack state of tracked NavHost. |

## Constructors

| | |
|---|---|
| [NavState](-nav-state.md) | [androidJvm]<br>fun [NavState](-nav-state.md)(controllersState: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavControllerState](../-nav-controller-state/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [controllersState](controllers-state.md) | [androidJvm]<br>val [controllersState](controllers-state.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavControllerState](../-nav-controller-state/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [find](find.md) | [androidJvm]<br>fun [find](find.md)(dest: [INavDestination](../-i-nav-destination/index.md)): [NavControllerState](../-nav-controller-state/index.md)?<br>Searches for controller that has [dest](find.md) in its backstack |
| [get](get.md) | [androidJvm]<br>fun [get](get.md)(id: [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md)): [NavControllerState](../-nav-controller-state/index.md)?<br>Returns a controller with provided [id](get.md) or null of no such controller can be found |
| [isCurrent](is-current.md) | [androidJvm]<br>fun [isCurrent](is-current.md)(destination: [INavDestination](../-i-nav-destination/index.md)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if [destination](is-current.md) is currently visible to the user in any of the tracked controllers. |
| [isInBackStack](is-in-back-stack.md) | [androidJvm]<br>fun [isInBackStack](is-in-back-stack.md)(dest: [INavDestination](../-i-nav-destination/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if provided destination is currently in back stack of any of the tracked controllers |
