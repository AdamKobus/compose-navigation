package com.adamkobus.compose.navigation.action

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.action.DiscardReason.NotDelivered
import com.adamkobus.compose.navigation.action.DiscardReason.NotMapped
import com.adamkobus.compose.navigation.action.DiscardReason.RejectedByVerifier
import com.adamkobus.compose.navigation.action.DiscardReason.Timeout
import com.adamkobus.compose.navigation.intent.NavIntent

/**
 * Used to indicate a result of [NavAction] or [NavIntent] processing.
 */
sealed class NavigationResult {
    /**
     * Indicates that navigation action was executed.
     */
    object Accepted : NavigationResult() {

        /**
         * Returns a formatted String representation of [Accepted]
         */
        override fun toString() = "Accepted"
    }

    /**
     * Indicates that action was discarded with a [reason]
     *
     * @param reason Provides information about the reason for which [NavAction] or [NavIntent] was rejected.
     */
    class Discarded(val reason: DiscardReason) : NavigationResult() {
        /**
         * Compares other [Discarded] by [reason] field
         */
        override fun equals(other: Any?): Boolean {
            return other is Discarded && other.reason == reason
        }

        /**
         * Builds hash code based on [reason] field
         */
        override fun hashCode(): Int {
            return reason.hashCode()
        }

        /**
         * Returns a formatted String representation of [NavigationResult]
         */
        override fun toString() = "Discarded[$reason]"
    }
}

/**
 * Represents a reason for which [NavAction] processing was discarded.
 *
 * @see RejectedByVerifier
 * @see NotMapped
 * @see NotDelivered
 * @see Timeout
 */
sealed class DiscardReason {
    /**
     * Indicates that an action was discarded by [verifier]
     *
     * @param verifier [NavActionVerifier] that discarded a [NavAction]
     */
    class RejectedByVerifier(val verifier: NavActionVerifier) : DiscardReason() {

        /**
         * Compares other [RejectedByVerifier] based on [verifier] field
         */
        override fun equals(other: Any?): Boolean {
            return other is RejectedByVerifier && other.verifier == verifier
        }

        /**
         * Builds hashCode based on [verifier] field
         */
        override fun hashCode(): Int {
            return verifier.hashCode()
        }

        /**
         * Returns a formatted String representation of [RejectedByVerifier]
         */
        override fun toString() = "RejectedByVerifier"
    }

    /**
     * Indicates that [NavIntent] could not be mapped to actual action
     */
    object NotMapped : DiscardReason() {

        /**
         * Returns a formatted String representation of [NotMapped]
         */
        override fun toString() = "NotMapped"
    }

    /**
     * Action could not be delivered because of invalid state
     */
    object NotDelivered : DiscardReason() {

        /**
         * Returns a formatted String representation of [NotDelivered]
         */
        override fun toString() = "NotDelivered"
    }

    /**
     * Change in the back stack didn't happen within the time limit.
     * The timeout has been added just as a safety measure for now,
     * so that the navigation processing queue is not blocked indefinitely if there is some unexpected issue.
     * This might be removed in future once the solution is proven tobe stable.
     */
    object Timeout : DiscardReason() {

        /**
         * Returns a formatted String representation of [Timeout]
         */
        override fun toString() = "Timeout"
    }
}
