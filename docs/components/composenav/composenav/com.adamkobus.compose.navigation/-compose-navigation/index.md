//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[ComposeNavigation](index.md)

# ComposeNavigation

[androidJvm]\
object [ComposeNavigation](index.md)

Provides methods to configure and interact with Compose Navigation library.

## Functions

| Name | Summary |
|---|---|
| [addNavActionVerifiers](add-nav-action-verifiers.md) | [androidJvm]<br>fun [addNavActionVerifiers](add-nav-action-verifiers.md)(vararg verifiers: [NavActionVerifier](../-nav-action-verifier/index.md)): [ComposeNavigation](index.md)<br>fun [addNavActionVerifiers](add-nav-action-verifiers.md)(verifiers: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[NavActionVerifier](../-nav-action-verifier/index.md)&gt;): [ComposeNavigation](index.md) |
| [addNavIntentResolvers](add-nav-intent-resolvers.md) | [androidJvm]<br>fun [addNavIntentResolvers](add-nav-intent-resolvers.md)(vararg resolvers: [NavIntentResolver](../-nav-intent-resolver/index.md)): [ComposeNavigation](index.md)<br>fun [addNavIntentResolvers](add-nav-intent-resolvers.md)(resolvers: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[NavIntentResolver](../-nav-intent-resolver/index.md)&gt;): [ComposeNavigation](index.md) |
| [disableRestrictedNamesCheck](disable-restricted-names-check.md) | [androidJvm]<br>fun [disableRestrictedNamesCheck](disable-restricted-names-check.md)(): [ComposeNavigation](index.md) |
| [getLogger](get-logger.md) | [androidJvm]<br>fun [getLogger](get-logger.md)(): [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md) |
| [getNavigationConsumer](get-navigation-consumer.md) | [androidJvm]<br>fun [getNavigationConsumer](get-navigation-consumer.md)(): [NavigationConsumer](../-navigation-consumer/index.md)<br>Provides an instance of [NavigationConsumer](../-navigation-consumer/index.md) - a class that accepts your navigation actions or intents. |
| [getNavigationStateSource](get-navigation-state-source.md) | [androidJvm]<br>fun [getNavigationStateSource](get-navigation-state-source.md)(): [NavigationStateSource](../-navigation-state-source/index.md) |
| [setLogger](set-logger.md) | [androidJvm]<br>fun [setLogger](set-logger.md)(logger: [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md)): [ComposeNavigation](index.md)<br>Changes the logger used by all [ComposeNavigation](index.md) components to [logger](set-logger.md) |
| [setLogLevel](set-log-level.md) | [androidJvm]<br>fun [setLogLevel](set-log-level.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [ComposeNavigation](index.md)<br>Tells the logger to use this log level. It's up to the logger itself to honor this setting or ignore it. |
| [setMainDispatcher](set-main-dispatcher.md) | [androidJvm]<br>fun [setMainDispatcher](set-main-dispatcher.md)(dispatcher: CoroutineDispatcher): [ComposeNavigation](index.md)<br>Compose Navigation library interacts with NavHostController on main thread. This is achieved using Dispatchers.Main dispatcher. This method allows you to change the dispatcher to your own. |

## Properties

| Name | Summary |
|---|---|
| [DEFAULT_LOG_LEVEL](-d-e-f-a-u-l-t_-l-o-g_-l-e-v-e-l.md) | [androidJvm]<br>val [DEFAULT_LOG_LEVEL](-d-e-f-a-u-l-t_-l-o-g_-l-e-v-e-l.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [DEFAULT_LOGGER](-d-e-f-a-u-l-t_-l-o-g-g-e-r.md) | [androidJvm]<br>val [DEFAULT_LOGGER](-d-e-f-a-u-l-t_-l-o-g-g-e-r.md): [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md) |
