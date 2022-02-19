package com.adamkobus.compose.navigation.model

internal interface ActionConsumer {
    val supportedGraphsRoutes: List<String>
    suspend fun awaitUntilReady()
}
