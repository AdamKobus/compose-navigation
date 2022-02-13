//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.error](../index.md)/[ReservedNameError](index.md)

# ReservedNameError

[androidJvm]\
class [ReservedNameError](index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [RuntimeException](https://developer.android.com/reference/kotlin/java/lang/RuntimeException.html)

This error is thrown if you attempt to use a name that starts with double underscore ('__'). Those names are reserved for internal use. If you still want to use names like that, you can disable this check with [ComposeNavigation.disableRestrictedNamesCheck](../../com.adamkobus.compose.navigation/-compose-navigation/disable-restricted-names-check.md)

## Constructors

| | |
|---|---|
| [ReservedNameError](-reserved-name-error.md) | [androidJvm]<br>fun [ReservedNameError](-reserved-name-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Inherited properties

| Name | Summary |
|---|---|
| [cause](index.md#-654012527%2FProperties%2F-1047480006) | [androidJvm]<br>open val [cause](index.md#-654012527%2FProperties%2F-1047480006): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [message](index.md#1824300659%2FProperties%2F-1047480006) | [androidJvm]<br>open val [message](index.md#1824300659%2FProperties%2F-1047480006): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |

## Inherited functions

| Name | Summary |
|---|---|
| [addSuppressed](index.md#282858770%2FFunctions%2F-1047480006) | [androidJvm]<br>fun [addSuppressed](index.md#282858770%2FFunctions%2F-1047480006)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](index.md#-1102069925%2FFunctions%2F-1047480006) | [androidJvm]<br>open fun [fillInStackTrace](index.md#-1102069925%2FFunctions%2F-1047480006)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](index.md#1043865560%2FFunctions%2F-1047480006) | [androidJvm]<br>open fun [getLocalizedMessage](index.md#1043865560%2FFunctions%2F-1047480006)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](index.md#2050903719%2FFunctions%2F-1047480006) | [androidJvm]<br>open fun [getStackTrace](index.md#2050903719%2FFunctions%2F-1047480006)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](index.md#672492560%2FFunctions%2F-1047480006) | [androidJvm]<br>fun [getSuppressed](index.md#672492560%2FFunctions%2F-1047480006)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](index.md#-418225042%2FFunctions%2F-1047480006) | [androidJvm]<br>open fun [initCause](index.md#-418225042%2FFunctions%2F-1047480006)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](index.md#-1769529168%2FFunctions%2F-1047480006) | [androidJvm]<br>open fun [printStackTrace](index.md#-1769529168%2FFunctions%2F-1047480006)()<br>open fun [printStackTrace](index.md#1841853697%2FFunctions%2F-1047480006)(p0: [PrintStream](https://developer.android.com/reference/kotlin/java/io/PrintStream.html))<br>open fun [printStackTrace](index.md#1175535278%2FFunctions%2F-1047480006)(p0: [PrintWriter](https://developer.android.com/reference/kotlin/java/io/PrintWriter.html)) |
| [setStackTrace](index.md#2135801318%2FFunctions%2F-1047480006) | [androidJvm]<br>open fun [setStackTrace](index.md#2135801318%2FFunctions%2F-1047480006)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt;) |
