//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[ComposeNavigation](index.md)/[setNavigationProcessingTimeout](set-navigation-processing-timeout.md)

# setNavigationProcessingTimeout

[androidJvm]\
fun [setNavigationProcessingTimeout](set-navigation-processing-timeout.md)(value: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))

This timeout is used to interrupt navigation processing tasks that are running for too long. In theory this should not happen. Timeout mechanism was introduced as a safety layer in case there is a bug in the library. Timeouts are logged as errors via [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md)

## Parameters

androidJvm

| | |
|---|---|
| value | timeout in ms |
