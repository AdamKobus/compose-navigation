package com.adamkobus.compose.navigation.destination

import org.junit.Assert.assertEquals
import org.junit.Test

class GlobalGraphTest {

    @Test
    fun `WHEN startDestination THEN proper destination is returned`() {
        assertEquals("__global__/__root__", GlobalGraph.startDestination().route.buildRoute())
    }

    @Test
    fun `WHEN name THEN proper name is returned`() {
        assertEquals("__global__", GlobalGraph.name)
    }

    @Test
    fun `WHEN Back Then proper destination is returned`() {
        assertEquals("__global__/__back__", GlobalGraph.Back.route.buildRoute())
    }
}
