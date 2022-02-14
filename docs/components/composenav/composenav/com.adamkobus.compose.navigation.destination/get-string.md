//[composenav](../../index.md)/[com.adamkobus.compose.navigation.destination](index.md)/[getString](get-string.md)

# getString

[androidJvm]\
fun [NavStackEntry](-nav-stack-entry/index.md)?.[getString](get-string.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

#### Return

String param embedded into this destination's route

## Parameters

androidJvm

| | |
|---|---|
| key | Name by which this param can be identified in destination's route |

## Throws

| | |
|---|---|
| [kotlin.NullPointerException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-null-pointer-exception/index.html) | if stack entry or arguments are null or argument with provided key does not exist |
