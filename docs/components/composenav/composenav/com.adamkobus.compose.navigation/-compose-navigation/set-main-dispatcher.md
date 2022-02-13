//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[ComposeNavigation](index.md)/[setMainDispatcher](set-main-dispatcher.md)

# setMainDispatcher

[androidJvm]\
fun [setMainDispatcher](set-main-dispatcher.md)(dispatcher: CoroutineDispatcher): [ComposeNavigation](index.md)

Compose Navigation library interacts with NavHostController on main thread. This is achieved using Dispatchers.Main dispatcher. This method allows you to change the dispatcher to a custom one.
