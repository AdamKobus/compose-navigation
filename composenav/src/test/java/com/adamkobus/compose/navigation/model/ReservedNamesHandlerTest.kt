package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.error.ReservedNameError
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ReservedNamesHandlerTest {
    private val testSubject = ReservedNamesHandler()

    @Test
    fun `GIVEN disabled WHEN reserved graph name THEN no error`() {
        // given
        testSubject.enabled = false

        // when
        testSubject.checkGraphName("__reserved")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN enabled WHEN reserved graph name THEN ReservedNameError is thrown`() {
        // given
        testSubject.enabled = true

        // when
        testSubject.checkGraphName("__reserved")
    }

    @Test
    fun `GIVEN enabled WHEN proper graph name THEN no error`() {
        // given
        testSubject.enabled = true

        // when
        testSubject.checkGraphName("proper")
    }

    @Test
    fun `GIVEN disabled WHEN reserved path name THEN no error`() {
        // given
        testSubject.enabled = false

        // when
        testSubject.checkPathName("__reserved")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN enabled WHEN reserved path name THEN ReservedNameError is thrown`() {
        // given
        testSubject.enabled = true

        // when
        testSubject.checkPathName("__reserved")
    }

    @Test
    fun `GIVEN enabled WHEN proper path name THEN no error`() {
        // given
        testSubject.enabled = true

        // when
        testSubject.checkPathName("proper")
    }

    @Test
    fun `GIVEN disabled WHEN reserved param name THEN no error`() {
        // given
        testSubject.enabled = false

        // when
        testSubject.checkParamName("__reserved")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN enabled WHEN reserved param name THEN ReservedNameError is thrown`() {
        // given
        testSubject.enabled = true

        // when
        testSubject.checkParamName("__reserved")
    }

    @Test
    fun `GIVEN enabled WHEN proper param name THEN no error`() {
        // given
        testSubject.enabled = true

        // when
        testSubject.checkParamName("proper")
    }

    @Test
    fun `GIVEN disabled WHEN reserved intent name THEN no error`() {
        // given
        testSubject.enabled = false

        // when
        testSubject.checkIntentName("__reserved")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN enabled WHEN reserved intent name THEN ReservedNameError is thrown`() {
        // given
        testSubject.enabled = true

        // when
        testSubject.checkIntentName("__reserved")
    }

    @Test
    fun `GIVEN enabled WHEN proper intent name THEN no error`() {
        // given
        testSubject.enabled = true

        // when
        testSubject.checkIntentName("proper")
    }

    @Test
    fun `GIVEN default state THEN enabled is true`() {
        assertTrue(testSubject.enabled)
    }
}
