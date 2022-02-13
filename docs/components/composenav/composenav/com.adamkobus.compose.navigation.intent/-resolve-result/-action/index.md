//[composenav](../../../../index.md)/[com.adamkobus.compose.navigation.intent](../../index.md)/[ResolveResult](../index.md)/[Action](index.md)

# Action

[androidJvm]\
class [Action](index.md)(action: [NavAction](../../../com.adamkobus.compose.navigation.action/-nav-action/index.md)) : [ResolveResult](../index.md)

Indicates that [NavIntent](../../-nav-intent/index.md) was resolved to the [action](action.md)

## Parameters

androidJvm

| | |
|---|---|
| action | the action that is a result of processing by [NavIntentResolver](../../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md) |

## Constructors

| | |
|---|---|
| [Action](-action.md) | [androidJvm]<br>fun [Action](-action.md)(action: [NavAction](../../../com.adamkobus.compose.navigation.action/-nav-action/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [action](action.md) | [androidJvm]<br>val [action](action.md): [NavAction](../../../com.adamkobus.compose.navigation.action/-nav-action/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares with other [Action](index.md) based on [action](action.md) field |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Generates hash code based on [action](action.md) field |
