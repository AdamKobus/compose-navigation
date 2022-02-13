//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavigateAction](index.md)/[NavigateAction](-navigate-action.md)

# NavigateAction

[androidJvm]\
fun [NavigateAction](-navigate-action.md)(navAction: [NavigateAction](index.md))

This constructor allows you to copy other [NavigateAction](index.md)

[androidJvm]\
fun [NavigateAction](-navigate-action.md)(fromDestination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), toDestination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList(), options: [NavOptions](../-nav-options/index.md)? = null)

## Parameters

androidJvm

| | |
|---|---|
| fromDestination | source back stack entry that initialized the action |
| toDestination | back stack entry that should be reached as a result of navigation |
| params | List of arguments that will be used to build toDestination formatted path. |
| options | [NavOptions](../-nav-options/index.md) that if present, will influence the way navigation is performed. |
