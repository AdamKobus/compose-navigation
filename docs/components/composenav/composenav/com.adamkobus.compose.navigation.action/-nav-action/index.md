//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavAction](index.md)

# NavAction

[androidJvm]\
abstract class [NavAction](index.md)(fromDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md))

Base class for all navigation actions

## Parameters

androidJvm

| | |
|---|---|
| fromDestination | source of the action |
| toDestination | target of the action |

## Constructors

| | |
|---|---|
| [NavAction](-nav-action.md) | [androidJvm]<br>fun [NavAction](-nav-action.md)(fromDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [fromDestination](from-destination.md) | [androidJvm]<br>val [fromDestination](from-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |
| [toDestination](to-destination.md) | [androidJvm]<br>val [toDestination](to-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |

## Functions

| Name | Summary |
|---|---|
| [asResult](as-result.md) | [androidJvm]<br>fun [asResult](as-result.md)(): [ResolveResult](../../com.adamkobus.compose.navigation.intent/-resolve-result/index.md)<br>Converts the action into [ResolveResult.Action](../../com.adamkobus.compose.navigation.intent/-resolve-result/-action/index.md) |
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares other [NavAction](index.md) by [fromDestination](from-destination.md) and [toDestination](to-destination.md) fields |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Generates hash code based on [fromDestination](from-destination.md) and [toDestination](to-destination.md) fields |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns String representation of [NavAction](index.md) |

## Inheritors

| Name |
|---|
| [NavigateAction](../-navigate-action/index.md) |
| [PopAction](../-pop-action/index.md) |
