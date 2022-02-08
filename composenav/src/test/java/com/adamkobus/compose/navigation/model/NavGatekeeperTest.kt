package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.VerifyResult
import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.destination.CurrentDestination
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class NavGatekeeperTest {

    @Test
    fun `GIVEN at least one verifier returns false WHEN isNavActionAllowed THEN returns false`() {
        // given
        val verifiers = setOf(createPassingVerifier(), createFailingVerifier())
        val testSubject = NavGatekeeper(verifiers)

        // when
        val obtained = testSubject.isNavActionAllowed(NAV_DESTINATION, NAV_ACTION)

        // then
        assertFalse(obtained)
    }

    @Test
    fun `GIVEN verifiers are empty WHEN isNavActionAllowed THEN returns true`() {
        // given
        val testSubject = NavGatekeeper(emptySet())

        // when
        val obtained = testSubject.isNavActionAllowed(NAV_DESTINATION, NAV_ACTION)

        // then
        assertTrue(obtained)
    }

    @Test
    fun `GIVEN all verifiers return true WHEN isNavActionAllowed THEN returns true`() {
        // given
        val verifiers = setOf(createPassingVerifier(), createPassingVerifier())
        val testSubject = NavGatekeeper(verifiers)

        // when
        val obtained = testSubject.isNavActionAllowed(NAV_DESTINATION, NAV_ACTION)

        // then
        assertTrue(obtained)
    }

    private fun createFailingVerifier(): NavActionVerifier =
        mockk<NavActionVerifier>().also {
            every { it.isNavActionAllowed(any(), any()) } returns VerifyResult.Discard
        }

    private fun createPassingVerifier(): NavActionVerifier =
        mockk<NavActionVerifier>().also {
            every { it.isNavActionAllowed(any(), any()) } returns VerifyResult.Allow
        }

    companion object {
        private val NAV_DESTINATION = mockk<CurrentDestination>()
        private val NAV_ACTION = mockk<NavigateAction>()
    }
}
