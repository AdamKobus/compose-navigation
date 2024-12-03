package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.error.ReservedNameError

internal class ReservedNamesHandler {
    internal var enabled = true

    fun checkGraphName(graphName: String) {
        if (enabled && graphName.startsWith(RESERVED_NAME_PREFIX)) {
            throw ReservedNameError(
                "Graph names starting with $RESERVED_NAME_PREFIX are reserved " +
                    "for internal usage. You can disable this with ComposeNavigation.disableRestrictedNamesCheck()",
            )
        }
    }

    fun checkPathName(pathName: String) {
        if (enabled && pathName.startsWith(RESERVED_NAME_PREFIX)) {
            throw ReservedNameError(
                "Path names starting with $RESERVED_NAME_PREFIX are reserved " +
                    "for internal usage. You can disable this with ComposeNavigation.disableRestrictedNamesCheck()",
            )
        }
    }

    fun checkParamName(paramName: String) {
        if (enabled && paramName.startsWith(RESERVED_NAME_PREFIX)) {
            throw ReservedNameError(
                "Param names starting with $RESERVED_NAME_PREFIX are reserved " +
                    "for internal usage. You can disable this with ComposeNavigation.disableRestrictedNamesCheck()",
            )
        }
    }

    fun checkIntentName(name: String) {
        if (enabled && name.startsWith(RESERVED_NAME_PREFIX)) {
            throw ReservedNameError(
                "Intent names starting with $RESERVED_NAME_PREFIX are reserved " +
                    "for internal usage. You can disable this with ComposeNavigation.disableRestrictedNamesCheck()",
            )
        }
    }

    companion object {
        private const val RESERVED_NAME_PREFIX = "__"
    }
}
