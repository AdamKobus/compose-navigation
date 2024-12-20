package com.adamkobus.compose.navigation.destination

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ScreenDestinationTest {
    private val graph: NavGraph = mockk()

    @Test
    fun `GIVEN using default route implementation WHEN route THEN proper route is returned`() {
        // given
        every { graph.name } returns "graph"
        val testSubject = screenDestination(graph, "path")

        // when
        val obtained = testSubject.route.buildRoute()

        // then
        assertEquals("graph/path", obtained)
    }
}
