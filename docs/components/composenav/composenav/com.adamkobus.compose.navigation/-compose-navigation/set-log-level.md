//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[ComposeNavigation](index.md)/[setLogLevel](set-log-level.md)

# setLogLevel

[androidJvm]\
fun [setLogLevel](set-log-level.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [ComposeNavigation](index.md)

Tells the logger to use this log level. It's up to the logger itself to honor this setting or ignore it.

## See also

androidJvm

| | |
|---|---|
| [com.adamkobus.compose.navigation.logger.NavLogLevel](../../com.adamkobus.compose.navigation.logger/-nav-log-level/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| level | Log level to be used by compose navigation library. This setting is cached and any logger provided via [setLogger](set-logger.md) method is asked to use it. |
