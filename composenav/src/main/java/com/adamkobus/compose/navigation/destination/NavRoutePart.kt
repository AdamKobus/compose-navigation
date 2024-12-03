package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.ComposeNavigation

/**
 * Used to declare apart of [ScreenDestination]'s route..
 */
sealed class NavRoutePart {
    /**
     * Declares a path of the route that contains name of a [NavGraph]
     *
     * @param name Name of a [NavGraph]
     */
    class GraphName internal constructor(val name: String, reservedNamesCheck: Boolean = true) : NavRoutePart() {
        /**
         * @param name Name of a [NavGraph]
         */
        constructor(name: String) : this(name, reservedNamesCheck = true)

        init {
            if (reservedNamesCheck) {
                ComposeNavigation.getReservedNamesHandler().checkGraphName(name)
            }
        }

        /**
         * @return formatted String representation of [GraphName]
         */
        override fun toString(): String = name

        /**
         * Compares other [GraphName] by [name] field
         */
        override fun equals(other: Any?): Boolean {
            return other is GraphName && other.name == name
        }

        /**
         * Builds hash code based on the [name] field
         */
        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    /**
     * Declares a static part of the route.
     *
     * @param name will become part of the final route without any modification
     */
    class Path internal constructor(val name: String, reservedNamesCheck: Boolean) : NavRoutePart() {
        /**
         * @param name will become part of the final route without any modification
         */
        constructor(name: String) : this(name, reservedNamesCheck = true)

        init {
            if (reservedNamesCheck) {
                ComposeNavigation.getReservedNamesHandler().checkPathName(name)
            }
        }

        /**
         * @return formatted String representation of [Path]
         */
        override fun toString(): String = name

        /**
         * Compares other [Path] by [name] field
         */
        override fun equals(other: Any?): Boolean {
            return other is Path && other.name == name
        }

        /**
         * Builds hash code based on the [name] field
         */
        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    /**
     * Defined a part of the route that will be substituted with param value during path building
     *
     * @param paramName name by which this param can be later obtained from [NavStackEntry]
     */
    class Param internal constructor(val paramName: String, reservedNamesCheck: Boolean) : NavRoutePart() {
        /**
         * @param paramName name by which this param can be later obtained from [NavStackEntry]
         */
        constructor(paramName: String) : this(paramName, true)

        init {
            if (reservedNamesCheck) {
                ComposeNavigation.getReservedNamesHandler().checkParamName(paramName)
            }
        }

        /**
         * @return formatted String representation of [Param]
         */
        override fun toString(): String = "{$paramName}"

        /**
         * Compares other [Param] by [paramName] field
         */
        override fun equals(other: Any?): Boolean {
            return other is Param && other.paramName == paramName
        }

        /**
         * Builds hash code based on the [paramName] field
         */
        override fun hashCode(): Int {
            return paramName.hashCode()
        }
    }
}
