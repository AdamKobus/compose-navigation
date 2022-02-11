package com.adamkobus.compose.navigation.data

import com.adamkobus.compose.navigation.NavActionVerifier

sealed class NavigationResult {
    /**
     * Indicates that navigation action was executed.
     */
    object Accepted : NavigationResult() {
        override fun toString() = "Accepted"
    }

    /**
     * Indicates that action was discarded with a [reason].
     */
    class Discarded(val reason: DiscardReason) : NavigationResult() {
        override fun equals(other: Any?): Boolean {
            return other is Discarded && other.reason == reason
        }

        override fun hashCode(): Int {
            return reason.hashCode()
        }

        override fun toString() = "Discarded[$reason]"
    }
}

sealed class DiscardReason {
    /**
     * Indicates that an action was discarded by [verifier]
     */
    class RejectedByVerifier(val verifier: NavActionVerifier) : DiscardReason() {
        override fun equals(other: Any?): Boolean {
            return other is RejectedByVerifier && other.verifier == verifier
        }

        override fun hashCode(): Int {
            return verifier.hashCode()
        }

        override fun toString() = "RejectedByVerifier"
    }

    /**
     * Indicates that [NavIntent] could not be mapped to actual action
     */
    object NotMapped : DiscardReason() {
        override fun toString() = "NotMapped"
    }

    /**
     * Action could not be delivered because of invalid state
     */
    object NotDelivered : DiscardReason() {
        override fun toString() = "NotDelivered"
    }

    /**
     * Change in the back stack didn't happen within the time limit.
     * The timeout has been added just as a safety measure for now,
     * so that the navigation processing queue is not blocked indefinitely if there is some unexpected issue.
     * This might be removed in future once the solution is proven tobe stable.
     */
    object Timeout : DiscardReason() {
        override fun toString() = "Timeout"
    }
}
