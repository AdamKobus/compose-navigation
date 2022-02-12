//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavAction](index.md)

# NavAction

[androidJvm]\
abstract class [NavAction](index.md)(fromDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md))

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [asResult](as-result.md) | [androidJvm]<br>fun [asResult](as-result.md)(): [ResolveResult](../../com.adamkobus.compose.navigation.data/-resolve-result/index.md) |
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [fromDestination](from-destination.md) | [androidJvm]<br>val [fromDestination](from-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |
| [toDestination](to-destination.md) | [androidJvm]<br>val [toDestination](to-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |

## Inheritors

| Name |
|---|
| [NavigateAction](../-navigate-action/index.md) |
| [PopAction](../-pop-action/index.md) |
