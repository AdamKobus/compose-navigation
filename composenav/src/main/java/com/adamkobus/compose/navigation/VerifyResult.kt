package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.action.NavAction

/**
 * Represents a result of [NavAction] verification
 */
enum class VerifyResult {
    /**
     * [NavAction] has been accepted by a verifier.
     * If all [NavActionVerifier]s return [Allow] result, then action will be sent for processing to NavHostController.
     */
    Allow,

    /**
     * [NavAction] has been discarded by a verifier and it will no longer be processed.
     */
    Discard
}
