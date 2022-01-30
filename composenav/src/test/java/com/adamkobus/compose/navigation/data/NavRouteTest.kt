package com.adamkobus.compose.navigation.data

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
}
