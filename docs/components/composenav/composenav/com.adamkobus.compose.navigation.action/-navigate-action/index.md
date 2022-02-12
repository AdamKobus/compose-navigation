//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavigateAction](index.md)

# NavigateAction

[androidJvm]\
class [NavigateAction](index.md)(fromDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, navigateWithController: ([NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?, navBuilder: [NavOptionsBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) : [NavAction](../-nav-action/index.md)

## Constructors

| | |
|---|---|
| [NavigateAction](-navigate-action.md) | [androidJvm]<br>fun [NavigateAction](-navigate-action.md)(navAction: [NavigateAction](index.md)) |

## Functions

| Name | Summary |
|---|---|
| [arg](arg.md) | [androidJvm]<br>infix fun [arg](arg.md)(param: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [NavigateAction](index.md)<br>infix fun [arg](arg.md)(param: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavigateAction](index.md) |
| [asResult](../-nav-action/as-result.md) | [androidJvm]<br>fun [asResult](../-nav-action/as-result.md)(): [ResolveResult](../../com.adamkobus.compose.navigation.data/-resolve-result/index.md) |
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [navigate](navigate.md) | [androidJvm]<br>infix fun [navigate](navigate.md)(param: [NavOptionsBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavigateAction](index.md) |
| [navigateWithController](navigate-with-controller.md) | [androidJvm]<br>infix fun [navigateWithController](navigate-with-controller.md)(param: ([NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavigateAction](index.md) |
| [plus](plus.md) | [androidJvm]<br>operator fun [plus](plus.md)(param: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [NavigateAction](index.md)<br>operator fun [plus](plus.md)(param: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavigateAction](index.md)<br>operator fun [plus](plus.md)(params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [NavigateAction](index.md) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [fromDestination](../-nav-action/from-destination.md) | [androidJvm]<br>val [fromDestination](../-nav-action/from-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |
| [toDestination](../-nav-action/to-destination.md) | [androidJvm]<br>val [toDestination](../-nav-action/to-destination.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |
