//[composenav](../../../../index.md)/[com.adamkobus.compose.navigation.action](../../index.md)/[NavigationResult](../index.md)/[Discarded](index.md)

# Discarded

[androidJvm]\
class [Discarded](index.md)(reason: [DiscardReason](../../-discard-reason/index.md)) : [NavigationResult](../index.md)

Indicates that action was discarded with a [reason](reason.md)

## Parameters

androidJvm

| | |
|---|---|
| reason | Provides information about the reason for which [NavAction](../../-nav-action/index.md) or [NavIntent](../../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md) was rejected. |

## Constructors

| | |
|---|---|
| [Discarded](-discarded.md) | [androidJvm]<br>fun [Discarded](-discarded.md)(reason: [DiscardReason](../../-discard-reason/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [reason](reason.md) | [androidJvm]<br>val [reason](reason.md): [DiscardReason](../../-discard-reason/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares other [Discarded](index.md) by [reason](reason.md) field |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Builds hash code based on [reason](reason.md) field |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a formatted String representation of [NavigationResult](../index.md) |
