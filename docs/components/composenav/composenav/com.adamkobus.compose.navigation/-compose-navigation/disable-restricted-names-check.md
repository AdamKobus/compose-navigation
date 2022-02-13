//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[ComposeNavigation](index.md)/[disableRestrictedNamesCheck](disable-restricted-names-check.md)

# disableRestrictedNamesCheck

[androidJvm]\
fun [disableRestrictedNamesCheck](disable-restricted-names-check.md)(): [ComposeNavigation](index.md)

By default, it's not possible to use destinations or intents names that start with double underscore - '__'. Trying to use such names results in [ReservedNameError](../../com.adamkobus.compose.navigation.error/-reserved-name-error/index.md) being thrown. You can disable this behaviour by invoking this function. Please keep in mind that this can be risky. Such names were reserved in advance in case they would be needed in future, so there is no guarantee they will keep working for you.
