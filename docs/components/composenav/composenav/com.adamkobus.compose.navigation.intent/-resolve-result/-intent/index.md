//[composenav](../../../../index.md)/[com.adamkobus.compose.navigation.intent](../../index.md)/[ResolveResult](../index.md)/[Intent](index.md)

# Intent

[androidJvm]\
class [Intent](index.md)(intent: [NavIntent](../../-nav-intent/index.md)) : [ResolveResult](../index.md)

Indicates that [NavIntent](../../-nav-intent/index.md) was resolved into another [intent](intent.md)

## Parameters

androidJvm

| | |
|---|---|
| intent | the intent that is a result of processing by [NavIntentResolver](../../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md) |

## Constructors

| | |
|---|---|
| [Intent](-intent.md) | [androidJvm]<br>fun [Intent](-intent.md)(intent: [NavIntent](../../-nav-intent/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [intent](intent.md) | [androidJvm]<br>val [intent](intent.md): [NavIntent](../../-nav-intent/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares with other [Intent](index.md) based on [intent](intent.md) field |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Generates hash code based on [intent](intent.md) field |
