//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[TabBarIntentResolver](index.md)/[TabBarIntentResolver](-tab-bar-intent-resolver.md)

# TabBarIntentResolver

[androidJvm]\
fun [TabBarIntentResolver](-tab-bar-intent-resolver.md)(tabsMapping: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md), [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md)&gt;, tabsRootGraph: [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md), popToTabHostIntent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)? = null, tabStateSavingBehaviour: [TabStateSavingBehaviour](../-tab-state-saving-behaviour/index.md) = SAVE_START_DESTINATION)

## Parameters

androidJvm

| | |
|---|---|
| tabsMapping | Assumption is that content displayed for each tab item lives in its own [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md). This map tells [TabBarIntentResolver](index.md) which tab item's graph should be opened for provided intents. |
| tabsRootGraph | Assumption is that tab items' graphs are nested in additional graph which is dedicated for tab host content only. This is required for this resolved to function properly. |
| popToTabHostIntent | optional intent. When used, [TabBarIntentResolver](index.md) will try to pop back stack to the latest destination in back stack that belongs to tab item content. |
| tabStateSavingBehaviour | Configures what parts of the tab item content should [TabBarIntentResolver](index.md) attempt to preserve during navigation |
