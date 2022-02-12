//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavActionVerifier](index.md)

# NavActionVerifier

[androidJvm]\
interface [NavActionVerifier](index.md)

Purpose of this class is to verify if [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) execution is allowed based on the application state.

As an example, it could be used to prevent duplicate nav action if user clicks list item twice.

[NavActionVerifier](index.md)s should be provided via dagger.multibindings.IntoSet bindings.

## Functions

| Name | Summary |
|---|---|
| [isNavActionAllowed](is-nav-action-allowed.md) | [androidJvm]<br>abstract fun [isNavActionAllowed](is-nav-action-allowed.md)(currentDestination: [CurrentDestination](../../com.adamkobus.compose.navigation.destination/-current-destination/index.md), action: [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)): [VerifyResult](../-verify-result/index.md) |
