package com.adamkobus.compose.navigation.ext

import androidx.lifecycle.Lifecycle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LifecycleEventObserverExtKtTest {

    private val onStart = mockk<() -> Unit>()
    private val onStop = mockk<() -> Unit>()

    @Before
    fun setUp() {
        every { onStart.invoke() } answers {}
        every { onStop.invoke() } answers {}
    }

    @Test
    fun `GIVEN ON_START event WHEN onStartStop THEN onStart is invoked`() {
        // when
        Lifecycle.Event.ON_START.onStartStop(onStart = onStart, onStop = onStop)

        // then
        verify(exactly = 1) { onStart() }
        verify(exactly = 0) { onStop() }
    }

    @Test
    fun `GIVEN ON_STOP event WHEN onStartStop THEN onStop is invoked`() {
        // when
        Lifecycle.Event.ON_STOP.onStartStop(onStart = onStart, onStop = onStop)

        // then
        verify(exactly = 0) { onStart() }
        verify(exactly = 1) { onStop() }
    }

    @Test
    fun `GIVEN ignored event WHEN onStartStop THEN neither onStart not onStop is invoked`() {
        // given
        val ignoredEvents = Lifecycle.Event.values().filter { it != Lifecycle.Event.ON_START && it != Lifecycle.Event.ON_STOP }
        ignoredEvents.forEach { event ->

            // when
            event.onStartStop(onStart = onStart, onStop = onStop)

            // then
            verify(exactly = 0) { onStart() }
            verify(exactly = 0) { onStop() }
        }
    }
}
