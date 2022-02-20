//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[ComposeNavigation](index.md)

# ComposeNavigation

[androidJvm]\
object [ComposeNavigation](index.md)

Main use of this class is to configure Compose Navigation library with:

- 
   [ComposeNavigation.addNavActionVerifiers](add-nav-action-verifiers.md)
- 
   [ComposeNavigation.addNavIntentResolvers](add-nav-intent-resolvers.md)
- 
   [ComposeNavigation.setLogger](set-logger.md)
- 
   [ComposeNavigation.setLogLevel](set-log-level.md)
- 
   [ComposeNavigation.setMainDispatcher](set-main-dispatcher.md)
- 
   [ComposeNavigation.setIoDispatcher](set-io-dispatcher.md)
- 
   [ComposeNavigation.disableRestrictedNamesCheck](disable-restricted-names-check.md)
- 
   [ComposeNavigation.setNavigationProcessingTimeout](set-navigation-processing-timeout.md)

It also gives you access to:

- 
   [NavigationConsumer](../-navigation-consumer/index.md) via [ComposeNavigation.getNavigationConsumer](get-navigation-consumer.md) method
- 
   [NavigationStateSource](../-navigation-state-source/index.md) via [ComposeNavigation.getNavigationStateSource](get-navigation-state-source.md)
- 
   Current [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md) via [ComposeNavigation.getLogger](get-logger.md)

## Properties

| Name | Summary |
|---|---|
| [DEFAULT_LOG_LEVEL](-d-e-f-a-u-l-t_-l-o-g_-l-e-v-e-l.md) | [androidJvm]<br>val [DEFAULT_LOG_LEVEL](-d-e-f-a-u-l-t_-l-o-g_-l-e-v-e-l.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default log level to which ComposeNavigation's logger is set to |
| [DEFAULT_LOGGER](-d-e-f-a-u-l-t_-l-o-g-g-e-r.md) | [androidJvm]<br>val [DEFAULT_LOGGER](-d-e-f-a-u-l-t_-l-o-g-g-e-r.md): [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md)<br>Default logger used by ComposeNavigation. |

## Functions

| Name | Summary |
|---|---|
| [addNavActionVerifiers](add-nav-action-verifiers.md) | [androidJvm]<br>fun [addNavActionVerifiers](add-nav-action-verifiers.md)(vararg verifiers: [NavActionVerifier](../-nav-action-verifier/index.md)): [ComposeNavigation](index.md)<br>fun [addNavActionVerifiers](add-nav-action-verifiers.md)(verifiers: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[NavActionVerifier](../-nav-action-verifier/index.md)&gt;): [ComposeNavigation](index.md)<br>Adds provided [verifiers](add-nav-action-verifiers.md) so that they can participate in [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)s processing. The verifiers are used in the order in which you're adding them |
| [addNavIntentResolvers](add-nav-intent-resolvers.md) | [androidJvm]<br>fun [addNavIntentResolvers](add-nav-intent-resolvers.md)(vararg resolvers: [NavIntentResolver](../-nav-intent-resolver/index.md)): [ComposeNavigation](index.md)<br>fun [addNavIntentResolvers](add-nav-intent-resolvers.md)(resolvers: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[NavIntentResolver](../-nav-intent-resolver/index.md)&gt;): [ComposeNavigation](index.md)<br>Adds provided [resolvers](add-nav-intent-resolvers.md) so that they can participate in [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)s processing. The resolvers are used in the order in which you're adding them |
| [disableRestrictedNamesCheck](disable-restricted-names-check.md) | [androidJvm]<br>fun [disableRestrictedNamesCheck](disable-restricted-names-check.md)(): [ComposeNavigation](index.md)<br>By default, it's not possible to use destinations or intents names that start with double underscore - '__'. Trying to use such names results in [ReservedNameError](../../com.adamkobus.compose.navigation.error/-reserved-name-error/index.md) being thrown. You can disable this behaviour by invoking this function. Please keep in mind that this can be risky. Such names were reserved in advance in case they would be needed in future, so there is no guarantee they will keep working for you. |
| [getLogger](get-logger.md) | [androidJvm]<br>fun [getLogger](get-logger.md)(): [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md) |
| [getNavigationConsumer](get-navigation-consumer.md) | [androidJvm]<br>fun [getNavigationConsumer](get-navigation-consumer.md)(): [NavigationConsumer](../-navigation-consumer/index.md) |
| [getNavigationStateSource](get-navigation-state-source.md) | [androidJvm]<br>fun [getNavigationStateSource](get-navigation-state-source.md)(navigationId: [NavigationId](../-navigation-id/index.md)): [NavigationStateSource](../-navigation-state-source/index.md) |
| [reset](reset.md) | [androidJvm]<br>fun [reset](reset.md)()<br>Resets the ComposeNavigation to initial state. This will reset: |
| [setIoDispatcher](set-io-dispatcher.md) | [androidJvm]<br>fun [setIoDispatcher](set-io-dispatcher.md)(dispatcher: CoroutineDispatcher): [ComposeNavigation](index.md)<br>IO Dispatcher is used to execute navigation actions and intents processing. This has to be set before any navigation processor is created. |
| [setLogger](set-logger.md) | [androidJvm]<br>fun [setLogger](set-logger.md)(logger: [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md)): [ComposeNavigation](index.md)<br>Changes the logger used by all [ComposeNavigation](index.md) components to [logger](set-logger.md) |
| [setLogLevel](set-log-level.md) | [androidJvm]<br>fun [setLogLevel](set-log-level.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [ComposeNavigation](index.md)<br>Tells the logger to use this log level. It's up to the logger itself to honor this setting or ignore it. |
| [setMainDispatcher](set-main-dispatcher.md) | [androidJvm]<br>fun [setMainDispatcher](set-main-dispatcher.md)(dispatcher: CoroutineDispatcher): [ComposeNavigation](index.md)<br>Compose Navigation library interacts with NavHostController on main thread. This is achieved using Dispatchers.Main dispatcher. This method allows you to change the dispatcher to a custom one. |
| [setNavigationProcessingTimeout](set-navigation-processing-timeout.md) | [androidJvm]<br>fun [setNavigationProcessingTimeout](set-navigation-processing-timeout.md)(value: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))<br>This timeout is used to interrupt navigation processing tasks that are running for too long. In theory this should not happen. Timeout mechanism was introduced as a safety layer in case there is a bug in the library. Timeouts are logged as errors via [NavLogger](../../com.adamkobus.compose.navigation.logger/-nav-logger/index.md) |
