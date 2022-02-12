//[composenav](../../index.md)/[com.adamkobus.compose.navigation](index.md)

# Package com.adamkobus.compose.navigation

## Types

| Name | Summary |
|---|---|
| [ComposeNavigation](-compose-navigation/index.md) | [androidJvm]<br>object [ComposeNavigation](-compose-navigation/index.md)<br>Provides methods to configure and interact with Compose Navigation library. |
| [NavActionVerifier](-nav-action-verifier/index.md) | [androidJvm]<br>interface [NavActionVerifier](-nav-action-verifier/index.md)<br>Purpose of this class is to verify if [NavigateAction](../com.adamkobus.compose.navigation.action/-navigate-action/index.md) execution is allowed based on the application state. |
| [NavigationConsumer](-navigation-consumer/index.md) | [androidJvm]<br>interface [NavigationConsumer](-navigation-consumer/index.md)<br>Should be used in your ViewModel to dispatch [NavAction](../com.adamkobus.compose.navigation.action/-nav-action/index.md)s or [NavIntent](../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)s. |
| [NavigationStateSource](-navigation-state-source/index.md) | [androidJvm]<br>interface [NavigationStateSource](-navigation-state-source/index.md) |
| [NavIntentResolver](-nav-intent-resolver/index.md) | [androidJvm]<br>interface [NavIntentResolver](-nav-intent-resolver/index.md) |
| [TabBarIntentResolver](-tab-bar-intent-resolver/index.md) | [androidJvm]<br>open class [TabBarIntentResolver](-tab-bar-intent-resolver/index.md)(tabsMapping: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[NavIntent](../com.adamkobus.compose.navigation.intent/-nav-intent/index.md), [NavGraph](../com.adamkobus.compose.navigation.data/-nav-graph/index.md)&gt;, tabsRootGraph: [NavGraph](../com.adamkobus.compose.navigation.data/-nav-graph/index.md), popToTabHostIntent: [NavIntent](../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)?, tabStateSavingBehaviour: [TabStateSavingBehaviour](-tab-state-saving-behaviour/index.md)) : [NavIntentResolver](-nav-intent-resolver/index.md)<br>Use this [NavIntentResolver](-nav-intent-resolver/index.md) to handle edge cases related to navigation using tab bar. |
| [TabStateSavingBehaviour](-tab-state-saving-behaviour/index.md) | [androidJvm]<br>enum [TabStateSavingBehaviour](-tab-state-saving-behaviour/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[TabStateSavingBehaviour](-tab-state-saving-behaviour/index.md)&gt; |
| [VerifyResult](-verify-result/index.md) | [androidJvm]<br>enum [VerifyResult](-verify-result/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[VerifyResult](-verify-result/index.md)&gt; |