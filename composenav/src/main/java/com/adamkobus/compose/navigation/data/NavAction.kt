package com.adamkobus.compose.navigation.data

data class NavAction(val fromDestination: INavDestination, val toDestination: INavDestination, val params: List<String> = emptyList()) {

    operator fun plus(params: List<String>): NavAction = copy(params = this.params + params)

    operator fun plus(param: Int): NavAction = plus(param.toString())

    operator fun plus(param: String): NavAction = copy(params = this.params + param)

    companion object {
        val Back = NavAction(GlobalDestination, PopBackStackDestination)
    }
}
