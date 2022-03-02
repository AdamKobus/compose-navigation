package com.adamkobus.compose.navigation.action

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavRoute
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class NavigateActionTest {

    private val controller: NavHostController = mockk()
    private val fromDestination: NavDestination = mockk()
    private val toDestination: NavDestination = mockk()
    private val route: NavRoute = mockk()

    private val routeSlot = slot<String>()
    private val paramsSlot = slot<List<String>>()

    private val baseTestSubject = NavigateAction(fromDestination, toDestination)

    @Before
    fun setUp() {
        every { controller.navigate(capture(routeSlot)) } answers {}
        every { toDestination.route } returns route
        every { route.buildPath(capture(paramsSlot)) } returns EXPECTED_PATH
    }

    @Test
    fun `GIVEN int param added WHEN navigate THEN param is used`() {
        // given
        val testSubject = baseTestSubject.arg(10)

        // when
        testSubject.navigate(controller)

        // then
        assertEquals(listOf("10"), paramsSlot.captured)
    }

    @Test
    fun `GIVEN long param added WHEN navigate THEN param is used`() {
        // given
        val testSubject = baseTestSubject.arg(10000000L)

        // when
        testSubject.navigate(controller)

        // then
        assertEquals(listOf("10000000"), paramsSlot.captured)
    }

    @Test
    fun `GIVEN String param added WHEN navigate THEN param is used`() {
        // given
        val testSubject = baseTestSubject.arg("asdf")

        // when
        testSubject.navigate(controller)

        // then
        assertEquals(listOf("asdf"), paramsSlot.captured)
    }

    @Test
    fun `GIVEN float param added WHEN navigate THEN param is used`() {
        // given
        val testSubject = baseTestSubject.arg(0.5f)

        // when
        testSubject.navigate(controller)

        // then
        assertEquals(listOf("0.5"), paramsSlot.captured)
    }

    @Test
    fun `GIVEN double param added WHEN navigate THEN param is used`() {
        // given
        val testSubject = baseTestSubject.arg(0.123)

        // when
        testSubject.navigate(controller)

        // then
        assertEquals(listOf("0.123"), paramsSlot.captured)
    }

    @Test
    fun `GIVEN Boolean param added WHEN navigate THEN param is used`() {
        // given
        val testSubject = baseTestSubject.arg(true)

        // when
        testSubject.navigate(controller)

        // then
        assertEquals(listOf("true"), paramsSlot.captured)
    }

    companion object {
        private const val EXPECTED_PATH = "expectedPath"
    }
}
