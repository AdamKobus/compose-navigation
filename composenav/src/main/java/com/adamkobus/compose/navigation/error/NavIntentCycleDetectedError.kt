package com.adamkobus.compose.navigation.error

/**
 * This error is thrown when a cycle has been detected during [com.adamkobus.compose.navigation.intent.NavIntent] processing.
 */
class NavIntentCycleDetectedError(message: String) : RuntimeException(message)
