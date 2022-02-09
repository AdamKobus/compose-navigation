package com.adamkobus.compose.navigation.intent

import com.adamkobus.compose.navigation.error.NavIntentCycleDetectedError
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class NavIntentHistoryTest {

    private val testSubject = NavIntentHistory(INITIAL_ACTION)

    @Test
    fun `GIVEN only single node preset WHEN adding intent with same name THEN NavIntentCycleDetectedError thrown`() {
        // when
        val error = assertThrows(NavIntentCycleDetectedError::class.java) {
            testSubject.addNode(NavIntent("initial"))
        }

        // then
        assertEquals("initial -> initial", error.message)
    }

    @Test
    fun `GIVEN multiple nodes preset WHEN adding node creates cycle THEN NavIntentCycleDetectedError thrown`() {
        // when
        val error = assertThrows(NavIntentCycleDetectedError::class.java) {
            testSubject.addNode(NavIntent("second"))
            testSubject.addNode(NavIntent("third"))
            testSubject.addNode(NavIntent("second"))
        }

        // then
        assertEquals("initial -> second -> third -> second", error.message)
    }

    @Test
    fun `GIVEN only initial node present WHEN adding new node without creating cycle THEN no error thrown`() {
        // when
        testSubject.addNode(NavIntent("third"))
    }

    @Test
    fun `GIVEN multiple nodes present WHEN adding new node without creating cycle THEN no error thrown`() {
        // given
        testSubject.addNode(NavIntent("second"))

        // when
        testSubject.addNode(NavIntent("third"))
    }

    companion object {
        private val INITIAL_ACTION = NavIntent("initial")
    }
}
