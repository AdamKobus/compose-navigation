package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.error.NavArgumentFormatInvalidError
import com.adamkobus.compose.navigation.error.NavArgumentMissingError
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class NavStackEntryTest {

    private val destination: NavDestination = mockk()
    private val arguments = mutableMapOf(
        "intArg" to "10",
        "longArg" to "100000000",
        "stringArg" to "asdf"
    )

    private val testSubject = NavStackEntry(destination, arguments)

    @Before
    fun setUp() {
        every { destination.toString() } returns "destinationString"
    }

    @Test
    fun `GIVEN String argument is present WHEN getString THEN expected value is returned`() {
        // when
        val obtained = testSubject.getString("stringArg")

        // then
        assertEquals("asdf", obtained)
    }

    @Test
    fun `GIVEN String argument is missing WHEN getString THEN NavArgumentMissingError is thrown`() {
        // when
        val error = assertThrows(NavArgumentMissingError::class.java) {
            testSubject.getString("missing")
        }

        // then
        assertEquals("Destination 'destinationString' did not receive value for argument with key 'missing'", error.message)
    }

    @Test
    fun `GIVEN Long argument is present WHEN getLong THEN expected value is returned`() {
        // when
        val obtained = testSubject.getLong("longArg")

        // then
        assertEquals(100000000L, obtained)
    }

    @Test
    fun `GIVEN Long argument is missing WHEN getLong THEN NavArgumentMissingError is thrown`() {
        // when
        val error = assertThrows(NavArgumentMissingError::class.java) {
            testSubject.getLong("missing")
        }

        // then
        assertEquals("Destination 'destinationString' did not receive value for argument with key 'missing'", error.message)
    }

    @Test
    fun `GIVEN Reading non-numeric argument WHEN getLong THEN NavArgumentFormatInvalidError is thrown`() {
        // when
        val error = assertThrows(NavArgumentFormatInvalidError::class.java) {
            testSubject.getLong("stringArg")
        }

        // then
        assertEquals(
            "Value 'asdf' of argument 'stringArg' in destination 'destinationString' could not be parsed to Long",
            error.message
        )
    }

    @Test
    fun `GIVEN Int argument is present WHEN getInt THEN expected value is returned`() {
        // when
        val obtained = testSubject.getInt("intArg")

        // then
        assertEquals(10, obtained)
    }

    @Test
    fun `GIVEN Int argument is missing WHEN getInt THEN NavArgumentMissingError is thrown`() {
        // when
        val error = assertThrows(NavArgumentMissingError::class.java) {
            testSubject.getInt("missing")
        }

        // then
        assertEquals("Destination 'destinationString' did not receive value for argument with key 'missing'", error.message)
    }

    @Test
    fun `GIVEN Reading non-numeric argument WHEN getInt THEN NavArgumentFormatInvalidError is thrown`() {
        // when
        val error = assertThrows(NavArgumentFormatInvalidError::class.java) {
            testSubject.getInt("stringArg")
        }

        // then
        assertEquals(
            "Value 'asdf' of argument 'stringArg' in destination 'destinationString' could not be parsed to Int",
            error.message
        )
    }
}
