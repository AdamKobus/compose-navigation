//[composenav](../../../../index.md)/[com.adamkobus.compose.navigation.data](../../index.md)/[DiscardReason](../index.md)/[Timeout](index.md)

# Timeout

[androidJvm]\
object [Timeout](index.md) : [DiscardReason](../index.md)

Change in the back stack didn't happen within the time limit. The timeout has been added just as a safety measure for now, so that the navigation processing queue is not blocked indefinitely if there is some unexpected issue. This might be removed in future once the solution is proven tobe stable.

## Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
