package com.adamkobus.compose.navigation.intent

import junit.framework.TestCase.assertEquals
import org.junit.Test

class NavIntentTest {

    @Test
    fun `GIVEN nav intent has argument WHEN get THEN argument value is returned`() {
        // given
        val subject = NavIntent("test")
        val argument = 2.2
        subject.addArgument("arg", argument)

        // when
        val obtained: Double = subject.getArgument("arg")

        // then
        assertEquals(argument, obtained)
    }

    @Test
    fun `GIVEN argument added via operator WHEN get THEN argument value is returned`() {
        // given
        val argument = 2.2
        val subject = NavIntent("test").arg("arg" to argument)

        // when
        val obtained: Double = subject.getArgument("arg")

        // then
        assertEquals(argument, obtained)
    }
}
