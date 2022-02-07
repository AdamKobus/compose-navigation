package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.ComposeNavigation

sealed class NavRoutePart {

    class GraphName internal constructor(val name: String, reservedNamesCheck: Boolean = true) : NavRoutePart() {

        constructor(name: String) : this(name, reservedNamesCheck = true)

        init {
            if (reservedNamesCheck) {
                ComposeNavigation.getReservedNamesHandler().checkGraphName(name)
            }
        }

        override fun toString(): String = name

        override fun equals(other: Any?): Boolean {
            return other is GraphName && other.name == name
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    class Path internal constructor(val name: String, reservedNamesCheck: Boolean) : NavRoutePart() {

        constructor(name: String) : this(name, reservedNamesCheck = true)

        init {
            if (reservedNamesCheck) {
                ComposeNavigation.getReservedNamesHandler().checkPathName(name)
            }
        }

        override fun toString(): String = name

        override fun equals(other: Any?): Boolean {
            return other is Path && other.name == name
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    /**
     * Defined a part of the route that will be substituted with param value during path building
     */
    class Param internal constructor(val paramName: String, reservedNamesCheck: Boolean) : NavRoutePart() {

        constructor(paramName: String) : this(paramName, true)

        init {
            if (reservedNamesCheck) {
                ComposeNavigation.getReservedNamesHandler().checkParamName(paramName)
            }
        }

        override fun toString(): String = "{$paramName}"

        override fun equals(other: Any?): Boolean {
            return other is Param && other.paramName == paramName
        }

        override fun hashCode(): Int {
            return paramName.hashCode()
        }
    }
}
