# Unreleased changes

### Fixes

- [#26](https://github.com/AdamKobus/compose-navigation/issues/26) `NavDestinationManager.currentDestination` change to the first visible
  screen happens instantly on app launch now.
  
### Changes

- Updated dependencies
- `NavActionVerifier` now returns VerifyResult enum instead of Boolean
- Improved the current destination evaluation code
- Prepared the code for handling nested / multiple NavHosts
- `INavAction` now has 2 different implementations: `NavigateAction` and `PopAction`
- `INavDestination` now has 3 different implementations: `NavGraph`, `NavDestination` and `PopDestination`. This makes it easy to determine
  which type of action should be performed.
- `NavActionWrapper` is now an abstract class so that there is less boilerplate code in NavActions declarations using sealed classes.
- `NavGraph` is now an abstract class that requires `name` to be passed in constructor
- `NavGraph` now has abstract method `startDestination()`
- `NavGraphBuilder.composableNavigation` no longer accepts `startDestination` as a param.
- `NavComposable` now accepts `vararg observedGraphs: NavGraph`. If any NavGraph is provided, then received actions will be filtered only to
  those with `fromDestination` belonging to provided graph(s)
- Added `NavLogger`
- Added `NavDestinationManager` - it provides information about current destination and back stack queue
- Added `ComposeNavigation` which should be used to configure the library and get access to its state
- Moved documentation to [docs](docs)
- Names starting with double underscore (i.e. '__myGraph') are now reserved for internal usage. 
  Trying to declare graph, path or param name like this will now result in `ReservedNameError` being thrown. 
  This behaviour can be disabled using `ComposeNavigation.disableRestrictedNamesCheck()`
- `NavActionConsumer` interface name was changed to `NavigationConsumer`
- Removed all `+` operators and changed the name of `to` infix function to `goTo` and `pop`.
  Using `to` was annoying sometimes because Kotlin would try to interpret it as map in some circumstances.
- Added `NavIntent`, `NavIntentResolver` and `ResolveResult`
- ComposeNavigation now has `addNavIntentResolvers()` method which you can use to register your own `NavIntentResolver`s 
- Added `TabBarIntentResolver` - an implementation of `NavIntentResolver` which handles many edge cases related to tab bar navigation.
- `NavAction` now has `asResult` method which produces `ResolveResult` - a class that indicates the processing result by `NavIntentResolver`
- All of the infix functions related to building `NavigateAction` and `PopAction` were moved to `INavDestination` class. 
  `PopDestination` throws `UnsupportedOperationException` if you attempt to use them with `PopDestination` as receiver.
- Removed dependency on Hilt. `ComposeNavigation` object is now responsible for providing all of the dependencies.
- Added `NavOptionsBuilder.popUpTo` extensions

# 0.1.0

### Changes

- Initial release
