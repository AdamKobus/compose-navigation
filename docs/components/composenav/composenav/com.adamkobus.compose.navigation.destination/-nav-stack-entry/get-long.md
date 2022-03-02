//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavStackEntry](index.md)/[getLong](get-long.md)

# getLong

[androidJvm]\
fun [getLong](get-long.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)

#### Return

Long value embedded into this destination's route

## Parameters

androidJvm

| | |
|---|---|
| key | Name by which this param can be identified in destination's route |

## Throws

| | |
|---|---|
| [com.adamkobus.compose.navigation.error.NavArgumentMissingError](../../com.adamkobus.compose.navigation.error/-nav-argument-missing-error/index.md) | if argument with provided key does not exist |
| [com.adamkobus.compose.navigation.error.NavArgumentFormatInvalidError](../../com.adamkobus.compose.navigation.error/-nav-argument-format-invalid-error/index.md) | if argument could not be parsed to Long |
