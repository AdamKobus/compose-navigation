package com.adamkobus.compose.navigation.error

import com.adamkobus.compose.navigation.ComposeNavigation

/**
 * This error is thrown if you attempt to use a name that starts with double underscore ('__'). Those names are reserved for internal use.
 * If you still want to use names like that, you can disable this check with [ComposeNavigation.disableRestrictedNamesCheck]
 */
class ReservedNameError(message: String) : RuntimeException(message)
