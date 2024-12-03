package com.adamkobus.compose.navigation

import junit.framework.TestCase.assertFalse
import org.junit.After
import org.junit.Test

class ComposeNavigationTest {
    @After
    fun cleanUp() {
        ComposeNavigation.reset()
    }

    @Test
    fun `WHEN disableRestrictedNamesCheck THEN ReservedNamesHandler is disabled`() {
        // when
        ComposeNavigation.disableRestrictedNamesCheck()

        // then
        assertFalse(ComposeNavigation.getReservedNamesHandler().enabled)
    }
}
