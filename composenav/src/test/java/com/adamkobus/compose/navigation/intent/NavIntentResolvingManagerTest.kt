package com.adamkobus.compose.navigation.intent

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.ResolveResult
import com.adamkobus.compose.navigation.destination.CurrentDestination
import com.adamkobus.compose.navigation.error.NavIntentCycleDetectedError
import com.adamkobus.compose.navigation.error.NavIntentMappingTooDeepError
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NavIntentResolvingManagerTest {

    private val actionProducingResolver: NavIntentResolver = mockk()
    private val intentProducingResolver: NavIntentResolver = mockk()
    private val cycleProducingResolver: NavIntentResolver = mockk()

    private val producedAction: NavAction = mockk()
    private val currentDestination: CurrentDestination = mockk()

    private val testSubject = NavIntentResolvingManager()

    @Before
    fun setUp() {
        val slot = slot<NavIntent>()
        coEvery { actionProducingResolver.resolve(capture(slot), any()) } coAnswers {
            if (slot.captured == actionProducingIntent) {
                ResolveResult.Action(producedAction)
            } else {
                ResolveResult.None
            }
        }
        coEvery { intentProducingResolver.resolve(capture(slot), any()) } coAnswers {
            val mapped = if (slot.captured == firstIntent) {
                secondIntent
            } else if (slot.captured == secondIntent) {
                thirdIntent
            } else if (slot.captured == thirdIntent) {
                actionProducingIntent
            } else {
                null
            }
            mapped?.let {
                ResolveResult.Intent(it)
            } ?: ResolveResult.None
        }
        coEvery { cycleProducingResolver.resolve(capture(slot), any()) } coAnswers {
            val mapped = if (slot.captured == firstIntent) {
                secondIntent
            } else if (slot.captured == secondIntent) {
                thirdIntent
            } else if (slot.captured == thirdIntent) {
                firstIntent
            } else {
                null
            }
            mapped?.let {
                ResolveResult.Intent(it)
            } ?: ResolveResult.None
        }
    }

    @Test
    fun `GIVEN only one resolver registered WHEN resolve intent that directly produces action THEN action is returned`() = runTest {
        // given
        testSubject.register(actionProducingResolver)

        // when
        val obtained = testSubject.resolve(actionProducingIntent, currentDestination)

        // then
        assertEquals(producedAction, obtained)
    }

    @Test
    fun `GIVEN multiple resolvers registered WHEN resolve intent that directly produces action THEN action is returned`() = runTest {
        // given
        testSubject.register(intentProducingResolver, actionProducingResolver)

        // when
        val obtained = testSubject.resolve(actionProducingIntent, currentDestination)

        // then
        assertEquals(producedAction, obtained)
    }

    @Test
    fun `GIVEN unknown intent WHEN resolve THEN null is returned`() = runTest {
        // given
        testSubject.register(intentProducingResolver, actionProducingResolver)

        // when
        val obtained = testSubject.resolve(unknownIntent, currentDestination)

        // then
        assertNull(obtained)
    }

    @Test
    fun `GIVEN finding actions requires multiple resolves WHEN resolve THEN action is returned`() = runTest {
        // given
        testSubject.register(intentProducingResolver, actionProducingResolver)

        // when
        val obtained = testSubject.resolve(firstIntent, currentDestination)

        // then
        assertEquals(producedAction, obtained)
    }

    @Test(expected = NavIntentCycleDetectedError::class)
    fun `GIVEN resolver produces cycle WHEN resolve THEN NavIntentCycleDetectedError thrown`() = runTest {
        // given
        testSubject.register(cycleProducingResolver)

        // when
        testSubject.resolve(firstIntent, currentDestination)
    }

    @Test(expected = NavIntentMappingTooDeepError::class)
    fun `GIVEN too many mappings required WHEN resolve THEN NavIntentMappingTooDeepError thrown`() = runTest {
        // given
        val firstIntent = createChain(51)

        // when
        testSubject.resolve(firstIntent, currentDestination)
    }

    @Test
    fun `GIVEN mappings number just at the depth limit WHEN resolve THEN no error`() = runTest {
        // given
        val firstIntent = createChain(50)

        // when
        testSubject.resolve(firstIntent, currentDestination)
    }

    private fun createChain(depth: Int): NavIntent {
        val firstIntent = NavIntent("0")
        var previousIntent = firstIntent
        val slot = slot<NavIntent>()
        for (i in 1..depth) { // 50 is the limit
            val original = previousIntent
            val nextIntent = NavIntent("$i")
            val resolver: NavIntentResolver = mockk()
            coEvery { resolver.resolve(capture(slot), any()) } coAnswers {
                if (slot.captured == original) ResolveResult.Intent(nextIntent) else ResolveResult.None
            }
            testSubject.register(resolver)
            previousIntent = nextIntent
        }
        return firstIntent
    }

    companion object {
        private val firstIntent = NavIntent("first")
        private val secondIntent = NavIntent("second")
        private val thirdIntent = NavIntent("third")
        private val actionProducingIntent = NavIntent("action")

        private val unknownIntent = NavIntent("unknown")
    }
}
