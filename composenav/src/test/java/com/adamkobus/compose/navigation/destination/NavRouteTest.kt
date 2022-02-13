package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.error.ReservedNameError
import org.junit.Assert.assertEquals
import org.junit.Test

class NavRouteTest {

    @Test
    fun `GIVEN route defined WHEN buildRoute THEN proper route is returned`() {
        // given
        val testSubject = navRoute("testGraph", "testPath")

        // when
        val obtained = testSubject.buildRoute()

        // then
        assertEquals("testGraph/testPath", obtained)
    }

    @Test
    fun `GIVEN route with params defined WHEN buildRoute THEN proper route is returned`() {
        // given
        val testSubject = navRoute("testGraph", "testPath") {
            param("param1")
            path("foobar")
            param("secondParam")
        }

        // when
        val obtained = testSubject.buildRoute()

        // then
        assertEquals("testGraph/testPath/{param1}/foobar/{secondParam}", obtained)
    }

    @Test
    fun `GIVEN route defined WHEN next THEN properly initialized route builder is returned`() {
        // given
        val testSubject = navRoute("testGraph", "testPath").next {
            param("param")
        }

        // when
        val obtained = testSubject.buildRoute()

        // then
        assertEquals("testGraph/testPath/{param}", obtained)
    }

    @Test
    fun `GIVEN route with custom separator WHEN buildRoute THEN custom separator is used`() {
        // given
        val testSubject = navRoute("testGraph", "testPath") {
            separator = "-"
        }

        // when
        val obtained = testSubject.buildRoute()

        // then
        assertEquals("testGraph-testPath", obtained)
    }

    @Test
    fun `GIVEN route without params WHEN buildPath THEN proper path is returned`() {
        // given
        val testSubject = navRoute("testGraph", "testPath")

        // when
        val obtained = testSubject.buildPath()

        // then
        assertEquals("testGraph/testPath", obtained)
    }

    @Test
    fun `GIVEN route with params WHEN buildPath THEN proper path is returned`() {
        // given
        val testSubject = navRoute("testGraph", "testPath") {
            param("param1")
            path("foobar")
            param("secondParam")
        }

        // when
        val obtained = testSubject.buildPath("aaa", "bbb")

        // then
        assertEquals("testGraph/testPath/aaa/foobar/bbb", obtained)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `GIVEN providing too few params WHEN buildPath THEN IllegalArgumentException is thrown`() {
        // given
        val testSubject = navRoute("testGraph", "testPath") {
            param("param1")
            path("foobar")
            param("secondParam")
        }

        // when
        testSubject.buildPath("aaa")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `GIVEN providing too many params WHEN buildPath THEN IllegalArgumentException is thrown`() {
        // given
        val testSubject = navRoute("testGraph", "testPath") {
            param("param1")
            path("foobar")
            param("secondParam")
        }

        // when
        testSubject.buildPath("aaa", "bbb", "ccc")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN reserved graph name WHEN navRoute THEN ReservedNameError is thrown`() {
        navRoute("__reserved")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN reserved path name WHEN navRoute THEN ReservedNameError is thrown`() {
        navRoute("proper", "__reserved")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN reserved param name WHEN navRoute THEN ReservedNameError is thrown`() {
        navRoute("proper", "proper") {
            param("__reserved")
        }
    }

    @Test
    fun `GIVEN using default builder with proper graph name WHEN init THEN no error`() {
        NavRoute.Builder("proper")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN using default builder with reserved graph name WHEN init THEN ReservedNameError is thrown`() {
        NavRoute.Builder("__reserved")
    }

    @Test
    fun `GIVEN using default builder with proper path name WHEN init THEN no error`() {
        NavRoute.Builder("properGraph", "proper")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN using default builder with reserved path name WHEN init THEN ReservedNameError is thrown`() {
        NavRoute.Builder("properGraph", "__reserved")
    }

    @Test
    fun `GIVEN using initialParts builder init WHEN adding proper path THEN no error`() {
        NavRoute.Builder(emptyList()).path("proper")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN using initialParts builder init WHEN adding reserved path THEN ReservedNameError is thrown`() {
        NavRoute.Builder(emptyList()).path("__reserved")
    }

    @Test
    fun `GIVEN using initialParts builder init WHEN adding proper param THEN no error`() {
        NavRoute.Builder(emptyList()).param("proper")
    }

    @Test(expected = ReservedNameError::class)
    fun `GIVEN using initialParts builder init WHEN adding reserved param THEN ReservedNameError is thrown`() {
        NavRoute.Builder(emptyList()).param("__reserved")
    }
}
