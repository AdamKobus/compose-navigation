//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavActionWrapper](index.md)

# NavActionWrapper

[androidJvm]\
open class [NavActionWrapper](index.md)(action: [NavAction](../-nav-action/index.md))

This can be used to build navigation actions using sealed class. The advantage of using [NavActionWrapper](index.md) is that it's a structure that can be offered to [NavigationConsumer](../../com.adamkobus.compose.navigation/-navigation-consumer/index.md). It also implements [equals](equals.md) and [hashCode](hash-code.md) methods, so that you don't have to do it in your sealed classes.

## Parameters

androidJvm

| | |
|---|---|
| action | actual action that will be processed when offered to [NavigationConsumer](../../com.adamkobus.compose.navigation/-navigation-consumer/index.md) |

## Constructors

| | |
|---|---|
| [NavActionWrapper](-nav-action-wrapper.md) | [androidJvm]<br>fun [NavActionWrapper](-nav-action-wrapper.md)(action: [NavAction](../-nav-action/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [action](action.md) | [androidJvm]<br>val [action](action.md): [NavAction](../-nav-action/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares other [NavActionWrapper](index.md) by [action](action.md) field |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Builds hash code based on [action](action.md) field |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
