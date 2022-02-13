//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[VerifyResult](index.md)

# VerifyResult

[androidJvm]\
enum [VerifyResult](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[VerifyResult](index.md)&gt; 

Represents a result of [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md) verification

## Entries

| | |
|---|---|
| [Discard](-discard/index.md) | [androidJvm]<br>[Discard](-discard/index.md)()<br>[NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md) has been discarded by a verifier and it will no longer be processed. |
| [Allow](-allow/index.md) | [androidJvm]<br>[Allow](-allow/index.md)()<br>[NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md) has been accepted by a verifier. If all [NavActionVerifier](../-nav-action-verifier/index.md)s return [Allow](-allow/index.md) result, then action will be sent for processing to NavHostController. |

## Inherited properties

| Name | Summary |
|---|---|
| [name](-allow/index.md#-372974862%2FProperties%2F-1047480006) | [androidJvm]<br>val [name](-allow/index.md#-372974862%2FProperties%2F-1047480006): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ordinal](-allow/index.md#-739389684%2FProperties%2F-1047480006) | [androidJvm]<br>val [ordinal](-allow/index.md#-739389684%2FProperties%2F-1047480006): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
