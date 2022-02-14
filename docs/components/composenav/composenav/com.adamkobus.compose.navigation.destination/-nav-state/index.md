//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavState](index.md)

# NavState

[androidJvm]\
data class [NavState](index.md)(currentDestination: [NavStackEntry](../-nav-stack-entry/index.md)?, backStack: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavStackEntry](../-nav-stack-entry/index.md)&gt;)

Represents the current state of the back stack

## Parameters

androidJvm

| | |
|---|---|
| currentDestination | -     the destination that is currently displayed to the user. |
| backStack | -     all of the destinations that are currently in the back stack, including graphs. |

## Constructors

| | |
|---|---|
| [NavState](-nav-state.md) | [androidJvm]<br>fun [NavState](-nav-state.md)(currentDestination: [NavStackEntry](../-nav-stack-entry/index.md)?, backStack: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavStackEntry](../-nav-stack-entry/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [backStack](back-stack.md) | [androidJvm]<br>val [backStack](back-stack.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavStackEntry](../-nav-stack-entry/index.md)&gt; |
| [currentDestination](current-destination.md) | [androidJvm]<br>val [currentDestination](current-destination.md): [NavStackEntry](../-nav-stack-entry/index.md)? |

## Functions

| Name | Summary |
|---|---|
| [isCurrent](is-current.md) | [androidJvm]<br>fun [isCurrent](is-current.md)(destination: [INavDestination](../-i-nav-destination/index.md)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if [destination](is-current.md) |
| [isInBackStack](is-in-back-stack.md) | [androidJvm]<br>fun [isInBackStack](is-in-back-stack.md)(dest: [NavDestination](../-nav-destination/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if provided destination is currently in back stack |
