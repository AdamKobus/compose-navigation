//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[PopAction](index.md)

# PopAction

[androidJvm]\
class [PopAction](index.md)(fromDestination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), toDestination: [PopDestination](../../com.adamkobus.compose.navigation.destination/-pop-destination/index.md)) : [NavAction](../-nav-action/index.md)

This action results in [NavHostController.popBackStack](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html#popbackstack) being called

## Constructors

| | |
|---|---|
| [PopAction](-pop-action.md) | [androidJvm]<br>fun [PopAction](-pop-action.md)(fromDestination: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md), toDestination: [PopDestination](../../com.adamkobus.compose.navigation.destination/-pop-destination/index.md) = GlobalGraph.Back) |

## Inherited properties

| Name | Summary |
|---|---|
| [fromDestination](../-nav-action/from-destination.md) | [androidJvm]<br>val [fromDestination](../-nav-action/from-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |
| [toDestination](../-nav-action/to-destination.md) | [androidJvm]<br>val [toDestination](../-nav-action/to-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |

## Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a formatted String representation of [PopAction](index.md) |

## Inherited functions

| Name | Summary |
|---|---|
| [asResult](../-nav-action/as-result.md) | [androidJvm]<br>fun [asResult](../-nav-action/as-result.md)(): [ResolveResult](../../com.adamkobus.compose.navigation.intent/-resolve-result/index.md)<br>Converts the action into [ResolveResult.Action](../../com.adamkobus.compose.navigation.intent/-resolve-result/-action/index.md) |
| [equals](../-nav-action/equals.md) | [androidJvm]<br>open operator override fun [equals](../-nav-action/equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares other [NavAction](../-nav-action/index.md) by [fromDestination](../-nav-action/from-destination.md) and [toDestination](../-nav-action/to-destination.md) fields |
| [hashCode](../-nav-action/hash-code.md) | [androidJvm]<br>open override fun [hashCode](../-nav-action/hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Generates hash code based on [fromDestination](../-nav-action/from-destination.md) and [toDestination](../-nav-action/to-destination.md) fields |
