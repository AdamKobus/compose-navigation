//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavigateAction](index.md)

# NavigateAction

[androidJvm]\
class [NavigateAction](index.md)(fromDestination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), toDestination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, options: [NavOptions](../-nav-options/index.md)?) : [NavAction](../-nav-action/index.md)

Represents an action that changes current destination to toDestination

## Parameters

androidJvm

| | |
|---|---|
| fromDestination | source back stack entry that initialized the action |
| toDestination | back stack entry that should be reached as a result of navigation |
| params | List of arguments that will be used to build toDestination formatted path. |
| options | [NavOptions](../-nav-options/index.md) that if present, will influence the way navigation is performed. |

## Constructors

| | |
|---|---|
| [NavigateAction](-navigate-action.md) | [androidJvm]<br>fun [NavigateAction](-navigate-action.md)(navAction: [NavigateAction](index.md))<br>This constructor allows you to copy other [NavigateAction](index.md) |
| [NavigateAction](-navigate-action.md) | [androidJvm]<br>fun [NavigateAction](-navigate-action.md)(fromDestination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), toDestination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList(), options: [NavOptions](../-nav-options/index.md)? = null) |

## Inherited properties

| Name | Summary |
|---|---|
| [fromDestination](../-nav-action/from-destination.md) | [androidJvm]<br>val [fromDestination](../-nav-action/from-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |
| [toDestination](../-nav-action/to-destination.md) | [androidJvm]<br>val [toDestination](../-nav-action/to-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |

## Functions

| Name | Summary |
|---|---|
| [arg](arg.md) | [androidJvm]<br>infix fun [arg](arg.md)(param: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [NavigateAction](index.md)<br>infix fun [arg](arg.md)(param: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavigateAction](index.md)<br>Provided [param](arg.md) will be delivered to toDestination |
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares with other [NavigateAction](index.md) by fromDestination, toDestination, params and options fields |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Generates hash code based on fromDestination, toDestination, params and options fields |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [withOptions](with-options.md) | [androidJvm]<br>infix fun [withOptions](with-options.md)(param: [NavOptions](../-nav-options/index.md)?): [NavigateAction](index.md)<br>Sets the options with which navigation action should be performed. Use [navActionOptions](../nav-action-options.md) builder to create new set of options. |

## Inherited functions

| Name | Summary |
|---|---|
| [asResult](../-nav-action/as-result.md) | [androidJvm]<br>fun [asResult](../-nav-action/as-result.md)(): [ResolveResult](../../com.adamkobus.compose.navigation.intent/-resolve-result/index.md)<br>Converts the action into [ResolveResult.Action](../../com.adamkobus.compose.navigation.intent/-resolve-result/-action/index.md) |
