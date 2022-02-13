//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[ComposeNavigation](index.md)/[addNavActionVerifiers](add-nav-action-verifiers.md)

# addNavActionVerifiers

[androidJvm]\
fun [addNavActionVerifiers](add-nav-action-verifiers.md)(vararg verifiers: [NavActionVerifier](../-nav-action-verifier/index.md)): [ComposeNavigation](index.md)

fun [addNavActionVerifiers](add-nav-action-verifiers.md)(verifiers: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[NavActionVerifier](../-nav-action-verifier/index.md)&gt;): [ComposeNavigation](index.md)

Adds provided [verifiers](add-nav-action-verifiers.md) so that they can participate in [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)s processing. The verifiers are used in the order in which you're adding them
