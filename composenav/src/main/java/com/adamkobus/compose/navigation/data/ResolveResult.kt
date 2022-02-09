package com.adamkobus.compose.navigation.data

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.intent.NavIntent

sealed class ResolveResult {
    class Action(val action: NavAction) : ResolveResult()
    class Intent(val intent: NavIntent) : ResolveResult()
    object None : ResolveResult()
}
