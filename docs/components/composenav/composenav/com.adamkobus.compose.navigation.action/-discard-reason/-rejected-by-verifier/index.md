//[composenav](../../../../index.md)/[com.adamkobus.compose.navigation.action](../../index.md)/[DiscardReason](../index.md)/[RejectedByVerifier](index.md)

# RejectedByVerifier

[androidJvm]\
class [RejectedByVerifier](index.md)(verifier: [NavActionVerifier](../../../com.adamkobus.compose.navigation/-nav-action-verifier/index.md)) : [DiscardReason](../index.md)

Indicates that an action was discarded by [verifier](verifier.md)

## Parameters

androidJvm

| | |
|---|---|
| verifier | [NavActionVerifier](../../../com.adamkobus.compose.navigation/-nav-action-verifier/index.md) that discarded a [NavAction](../../-nav-action/index.md) |

## Constructors

| | |
|---|---|
| [RejectedByVerifier](-rejected-by-verifier.md) | [androidJvm]<br>fun [RejectedByVerifier](-rejected-by-verifier.md)(verifier: [NavActionVerifier](../../../com.adamkobus.compose.navigation/-nav-action-verifier/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [verifier](verifier.md) | [androidJvm]<br>val [verifier](verifier.md): [NavActionVerifier](../../../com.adamkobus.compose.navigation/-nav-action-verifier/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares other [RejectedByVerifier](index.md) based on [verifier](verifier.md) field |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Builds hashCode based on [verifier](verifier.md) field |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a formatted String representation of [RejectedByVerifier](index.md) |
