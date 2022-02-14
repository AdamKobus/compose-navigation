//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.logger](../index.md)/[NavLogger](index.md)

# NavLogger

[androidJvm]\
interface [NavLogger](index.md)

Compose navigation library uses implementation of this interface to log diagnostics information and errors. You can provide your own implementation via [ComposeNavigation.setLogger](../../com.adamkobus.compose.navigation/-compose-navigation/set-logger.md)

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [d](d.md) | [androidJvm]<br>abstract fun [d](d.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>abstract fun [d](d.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Used to log current destination changes |
| [e](e.md) | [androidJvm]<br>abstract fun [e](e.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>abstract fun [e](e.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Used to log unexpected errors |
| [setLogLevel](set-log-level.md) | [androidJvm]<br>abstract fun [setLogLevel](set-log-level.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>When set, then logger should log only the messages with matching or higher importance than [level](set-log-level.md). Setting it to [DISABLED](-companion/-d-i-s-a-b-l-e-d.md) will disable all logging. |
| [v](v.md) | [androidJvm]<br>abstract fun [v](v.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>abstract fun [v](v.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Used to log details about navigation actions processing, i.e. when processing of the new actions has started and finished |
| [w](w.md) | [androidJvm]<br>abstract fun [w](w.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>abstract fun [w](w.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Used to log warnings, i.e. about intents or actions that were discarded |
