package com.adamkobus.compose.navigation.destination

/**
 * Creates a definition of a route representing a destination in your application.
 */
data class NavRoute constructor(
    private val parts: List<NavRoutePart>,
    private val separator: String
) {

    private val paramsCount: Int by lazy { parts.count { it is NavRoutePart.Param } }

    /**
     * @return route definition that can be later used with TODO refer class from Jetpack navigation
     */
    fun buildRoute(): String {
        return parts.joinToString(separator = separator)
    }

    /**
     * @return Path build based on the route definition and provided [params]
     *
     * @throws [IllegalArgumentException] if number of provided params doesn't match the expected params count.
     */
    fun buildPath(vararg params: String): String = buildPath(params.toList())

    /**
     * @return Path build based on the route definition and provided [params]
     *
     * @throws [IllegalArgumentException] if number of provided params doesn't match the expected params count.
     */
    fun buildPath(params: List<String>): String {
        if (params.size != paramsCount) {
            throw IllegalArgumentException("Wrong number of params, expected $paramsCount but got ${params.size} | $params")
        }
        var currentParamIndex = 0
        return parts.joinToString(separator = separator) {
            when (it) {
                is NavRoutePart.GraphName -> it.name
                is NavRoutePart.Path -> it.name
                is NavRoutePart.Param -> params[currentParamIndex++]
            }
        }
    }

    /**
     * Starts building a new route using builder that is populated with [NavRoutePart]s from the [NavRoute] on which [next] was invoked.
     */
    fun next(init: Builder.() -> Unit = {}): NavRoute {
        val builder = Builder(parts)
        builder.init()
        return builder.build()
    }

    class Builder internal constructor(initialParts: List<NavRoutePart>) {
        private val parts = initialParts.toMutableList()
        var separator = PART_SEPARATOR

        constructor(graphName: String) : this(emptyList()) {
            graphName(graphName)
        }

        constructor(graphName: String, pathName: String) : this(emptyList()) {
            graphName(graphName)
            path(pathName)
        }

        private fun graphName(name: String) {
            ensureNameNotEmpty(name)
            parts.add(NavRoutePart.GraphName(name))
        }

        fun path(name: String) {
            ensureNameNotEmpty(name)
            parts.add(NavRoutePart.Path(name))
        }

        fun param(name: String) {
            ensureNameNotEmpty(name)
            parts.add(NavRoutePart.Param(name))
        }

        private fun ensureNameNotEmpty(name: String) {
            if (name.isEmpty()) {
                throw IllegalArgumentException("Name must not be empty")
            }
        }

        private fun ensureGraphNameAdded() {
            if (parts.isEmpty() || parts[0] !is NavRoutePart.GraphName) {
                throw IllegalStateException("Must add graphName as first part of the path")
            }
        }

        fun build(): NavRoute {
            ensureGraphNameAdded()
            return NavRoute(parts = parts, separator = separator)
        }
    }

    companion object {
        private const val PART_SEPARATOR = "/"
    }
}

fun navRoute(graphName: String, init: NavRoute.Builder.() -> Unit = {}): NavRoute {
    val builder = NavRoute.Builder(graphName = graphName)
    builder.init()
    return builder.build()
}

fun navRoute(graphName: String, path: String, init: NavRoute.Builder.() -> Unit = {}): NavRoute {
    val builder = NavRoute.Builder(graphName = graphName, pathName = path)
    builder.init()
    return builder.build()
}

sealed class NavRoutePart {

    class GraphName(val name: String) : NavRoutePart() {
        override fun toString(): String = name

        override fun equals(other: Any?): Boolean {
            return other is GraphName && other.name == name
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    class Path(val name: String) : NavRoutePart() {
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
    class Param(val paramName: String) : NavRoutePart() {
        override fun toString(): String = "{$paramName}"

        override fun equals(other: Any?): Boolean {
            return other is Param && other.paramName == paramName
        }

        override fun hashCode(): Int {
            return paramName.hashCode()
        }
    }
}
