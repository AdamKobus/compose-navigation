//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[TabBarIntentResolver](index.md)

# TabBarIntentResolver

[androidJvm]\
open class [TabBarIntentResolver](index.md)(tabsMapping: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md), [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md)&gt;, tabsRootGraph: [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md), popToTabHostIntent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)?, tabStateSavingBehaviour: [TabStateSavingBehaviour](../-tab-state-saving-behaviour/index.md)) : [NavIntentResolver](../-nav-intent-resolver/index.md)

Use this [NavIntentResolver](../-nav-intent-resolver/index.md) to handle edge cases related to navigation using tab bar.

Cases handled by this resolver:

1. 
   Back stack doesn't contain tab bar root graph - no action is produced as this is not supported by this resolver
2. 
   Current destination already matches the starting destination linked of the graph to which given intent maps. No action is taken in such case
3. 
   Starting destination of the graph mapped with tabsMapping from the processed intent is already present in back stack. Back stack will be popped to starting destination.
4. 
   Current destination doesn't belong to any of the tab items' graphs. No action will be taken. It can happen if someone clicked tab item at the same item as some other interaction happened which opened new flow in the app.
5. 
   Current destination belongs to different tab item. In this case navigation will happen. Also, the state of the current tab will be saved depending on tabStateSavingBehaviour

## See also

androidJvm

| | |
|---|---|
| [com.adamkobus.compose.navigation.TabStateSavingBehaviour](../-tab-state-saving-behaviour/index.md) |  |
| [com.adamkobus.compose.navigation.NavIntentResolver](../-nav-intent-resolver/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| tabsMapping | Assumption is that content displayed for each tab item lives in its own [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md). This map tells [TabBarIntentResolver](index.md) which tab item's graph should be opened for provided intents. |
| tabsRootGraph | Assumption is that tab items' graphs are nested in additional graph which is dedicated for tab host content only. This is required for this resolved to function properly. |
| popToTabHostIntent | optional intent. When used, [TabBarIntentResolver](index.md) will try to pop back stack to the latest destination in back stack that belongs to tab item content. |
| tabStateSavingBehaviour | Configures what parts of the tab item content should [TabBarIntentResolver](index.md) attempt to preserve during navigation |

## Constructors

| | |
|---|---|
| [TabBarIntentResolver](-tab-bar-intent-resolver.md) | [androidJvm]<br>fun [TabBarIntentResolver](-tab-bar-intent-resolver.md)(tabsMapping: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md), [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md)&gt;, tabsRootGraph: [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md), popToTabHostIntent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)? = null, tabStateSavingBehaviour: [TabStateSavingBehaviour](../-tab-state-saving-behaviour/index.md) = SAVE_START_DESTINATION) |

## Functions

| Name | Summary |
|---|---|
| [resolve](resolve.md) | [androidJvm]<br>open suspend override fun [resolve](resolve.md)(intent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md), currentDestination: [CurrentDestination](../../com.adamkobus.compose.navigation.destination/-current-destination/index.md)): [ResolveResult](../../com.adamkobus.compose.navigation.data/-resolve-result/index.md)<br>This method should return one of the availabel results in [ResolveResult](../../com.adamkobus.compose.navigation.data/-resolve-result/index.md) |
