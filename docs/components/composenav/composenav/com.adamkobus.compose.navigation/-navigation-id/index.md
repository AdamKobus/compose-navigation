//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavigationId](index.md)

# NavigationId

[androidJvm]\
data class [NavigationId](index.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Uniquely identifies [ComposeNavHost](../-compose-nav-host.md) or [NavComposable](../../com.adamkobus.compose.navigation.ui/-nav-composable.md) across the application. Use this ID to query information that is related to those components, i.e. by providing it as param to [ComposeNavigation.getNavigationStateSource](../-compose-navigation/get-navigation-state-source.md).

Using same [NavigationId](index.md) with multiple components can lead to crashes or unexpected behaviour.

## Parameters

androidJvm

| | |
|---|---|
| id | value of this field will be used to identify [ComposeNavHost](../-compose-nav-host.md) or [NavComposable](../../com.adamkobus.compose.navigation.ui/-nav-composable.md) |

## Constructors

| | |
|---|---|
| [NavigationId](-navigation-id.md) | [androidJvm]<br>fun [NavigationId](-navigation-id.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [androidJvm]<br>val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns string representation of [NavigationId](index.md) |
