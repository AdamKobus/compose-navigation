package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.error.ReservedNameError
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NavRoutePartTest {
    @Test(expected = ReservedNameError::class)
    fun `GIVEN reserved graph name WHEN GraphName THEN ReservedNameError is thrown`() {
        NavRoutePart.GraphName("__reserved")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN reserved path name WHEN Path THEN ReservedNameError is thrown`() {
        NavRoutePart.Path("__reserved")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN reserved param name WHEN Param THEN ReservedNameError is thrown`() {
        NavRoutePart.Param("__reserved")
    }

    @Test
    fun `GIVEN proper graph name WHEN GraphName THEN ReservedNameError is thrown`() {
        // given
        val expected = "properName"

        // when
        val obtained = NavRoutePart.GraphName(expected)

        // then
        assertEquals(expected, obtained.name)
    }

    @Test
    fun `GIVEN proper path name WHEN Path THEN ReservedNameError is thrown`() {
        // given
        val expected = "properName"

        // when
        val obtained = NavRoutePart.Path(expected)

        // then
        assertEquals(expected, obtained.name)
    }

    @Test
    fun `GIVEN proper param name WHEN Param THEN ReservedNameError is thrown`() {
        // given
        val expected = "properName"

        // when
        val obtained = NavRoutePart.Param(expected)

        // then
        assertEquals(expected, obtained.paramName)
    }
}
