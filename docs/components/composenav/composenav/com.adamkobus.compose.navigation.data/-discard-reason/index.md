//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.data](../index.md)/[DiscardReason](index.md)

# DiscardReason

[androidJvm]\
sealed class [DiscardReason](index.md)

## Types

| Name | Summary |
|---|---|
| [NotDelivered](-not-delivered/index.md) | [androidJvm]<br>object [NotDelivered](-not-delivered/index.md) : [DiscardReason](index.md)<br>Action could not be delivered because of invalid state |
| [NotMapped](-not-mapped/index.md) | [androidJvm]<br>object [NotMapped](-not-mapped/index.md) : [DiscardReason](index.md)<br>Indicates that NavIntent could not be mapped to actual action |
| [RejectedByVerifier](-rejected-by-verifier/index.md) | [androidJvm]<br>class [RejectedByVerifier](-rejected-by-verifier/index.md)(verifier: [NavActionVerifier](../../com.adamkobus.compose.navigation/-nav-action-verifier/index.md)) : [DiscardReason](index.md)<br>Indicates that an action was discarded by [verifier](-rejected-by-verifier/verifier.md) |
| [Timeout](-timeout/index.md) | [androidJvm]<br>object [Timeout](-timeout/index.md) : [DiscardReason](index.md)<br>Change in the back stack didn't happen within the time limit. The timeout has been added just as a safety measure for now, so that the navigation processing queue is not blocked indefinitely if there is some unexpected issue. This might be removed in future once the solution is proven tobe stable. |

## Inheritors

| Name |
|---|
| [RejectedByVerifier](-rejected-by-verifier/index.md) |
| [NotMapped](-not-mapped/index.md) |
| [NotDelivered](-not-delivered/index.md) |
| [Timeout](-timeout/index.md) |
