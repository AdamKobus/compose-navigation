package com.adamkobus.compose.navigation.data

data class NavAction(val fromDestination: INavDestination, val toDestination: INavDestination, val params: List<String> = emptyList()) {

    operator fun plus(params: List<String>): NavAction = copy(params = this.params + params)

    operator fun plus(param: Int): NavAction = plus(param.toString())

    operator fun plus(param: String): NavAction = copy(params = this.params + param)

    infix fun arg(param: Int): NavAction = plus(param)

    infix fun arg(param: String): NavAction = plus(param)

    companion object {
        val Back = NavAction(GlobalDestination, PopBackStackDestination)
    }
}
