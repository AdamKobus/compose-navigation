//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavActionVerifier](index.md)

# NavActionVerifier

[androidJvm]\
interface [NavActionVerifier](index.md)

Purpose of this class is to verify if [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md) execution is allowed based on the application state.

As an example, it could be used to prevent duplicate nav action if user clicks list item twice.

## Functions

| Name | Summary |
|---|---|
| [isNavActionAllowed](is-nav-action-allowed.md) | [androidJvm]<br>abstract fun [isNavActionAllowed](is-nav-action-allowed.md)(navState: [NavState](../../com.adamkobus.compose.navigation.destination/-nav-state/index.md), action: [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)): [VerifyResult](../-verify-result/index.md) |
