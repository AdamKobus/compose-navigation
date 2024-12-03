package com.adamkobus.compose.navigation.intent

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.action.NavAction

/**
 * Produced as a result of [NavIntent] processing by [NavIntentResolver]s
 */
sealed class ResolveResult {
    /**
     * Indicates that [NavIntent] was resolved to the [action]
     *
     * @param action the action that is a result of processing by [NavIntentResolver]
     */
    class Action(val action: NavAction) : ResolveResult() {
        /**
         * Compares with other [Action] based on [action] field
         */
        override fun equals(other: Any?): Boolean {
            return other is Action && other.action == action
        }

        /**
         * Generates hash code based on [action] field
         */
        override fun hashCode(): Int {
            return action.hashCode()
        }
    }

    /**
     * Indicates that [NavIntent] was resolved into another [intent]
     *
     * @param intent the intent that is a result of processing by [NavIntentResolver]
     */
    class Intent(val intent: NavIntent) : ResolveResult() {
        /**
         * Compares with other [Intent] based on [intent] field
         */
        override fun equals(other: Any?): Boolean {
            return other is Intent && other.intent == intent
        }

        /**
         * Generates hash code based on [intent] field
         */
        override fun hashCode(): Int {
            return intent.hashCode()
        }
    }

    /**
     * Indicates that [NavIntentResolver] did not know how to process provided [NavIntent]
     */
    object None : ResolveResult()
}
