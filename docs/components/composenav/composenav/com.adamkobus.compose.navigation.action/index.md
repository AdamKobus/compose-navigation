//[composenav](../../index.md)/[com.adamkobus.compose.navigation.action](index.md)

# Package com.adamkobus.compose.navigation.action

## Types

| Name | Summary |
|---|---|
| [NavAction](-nav-action/index.md) | [androidJvm]<br>abstract class [NavAction](-nav-action/index.md)(fromDestination: [INavDestination](../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [INavDestination](../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)) |
| [NavActionWrapper](-nav-action-wrapper/index.md) | [androidJvm]<br>open class [NavActionWrapper](-nav-action-wrapper/index.md)(action: [NavAction](-nav-action/index.md)) |
| [NavigateAction](-navigate-action/index.md) | [androidJvm]<br>class [NavigateAction](-navigate-action/index.md)(fromDestination: [INavDestination](../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [INavDestination](../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, navigateWithController: ([NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?, navBuilder: [NavOptionsBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) : [NavAction](-nav-action/index.md) |
| [PopAction](-pop-action/index.md) | [androidJvm]<br>class [PopAction](-pop-action/index.md)(fromDestination: [INavDestination](../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md), toDestination: [PopDestination](../com.adamkobus.compose.navigation.destination/-pop-destination/index.md), navigate: [NavOptionsBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavOptionsBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) : [NavAction](-nav-action/index.md) |
