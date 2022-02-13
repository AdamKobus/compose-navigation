package com.adamkobus.compose.navigation.error

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.intent.NavIntent

/**
 * [NavIntent]s are processed by [NavIntentResolver]s recursively.
 * This error is thrown if recursive calls depths exceeds a limit (by default 50).
 */
class NavIntentMappingTooDeepError(message: String) : RuntimeException(message)
