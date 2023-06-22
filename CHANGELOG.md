# Unreleased changes

### Fixes



### Changes

# 0.3.2

### Changes

- Added `LocalNavDestination` - composition context that provides the `NavDestination` used to render the current screen
- Changes in `NavIntent` API - `get` function was renamed to `getArgument` and `addArgument` no longer creates a copy of `NavIntent`

# 0.3.1

### Changes

- Updated Kotlin version to 1.8.21
- Updated Jetpack Compose version to 1.4.3
- Updated Java version to 17
- Updated Accompanist version to 0.30.1

# 0.3.0

### Changes

- Added `NavDestination.popUpTo` which is equivalent of `NavHostController.popBackStack(route, saveState, inclusive)`
- `NavRoute` and `NavRouteBuilder` no longer accept custom path separators. Separator is now hardcoded to '/'
- Added `ComposeNavHost`. It is now a recommended way to declare NavHost
- Introduced `NavigationId` which should be used to differentiate separate NavHostControllers / NavHosts.
- `ComposeNavigation.getNavigationStateSource()` now requires `NavigationId` param
- Tasks processing now has a configurable timeout. By default it's set to 1000ms. 
  You can change it to your own value via `ComposeNavigation.setNavigationProcessingTimeout`
- Improved stability of navigation processor initialization logic.
- Added `ComposeNavigation.setIoDispatcher`
- Added support for nested Nav Hosts
- NavState now has a list of `NavControllerState`s which gives access to information about all used NavHosts
- `NavIntent` has an additional field now which is called `navigationId`.
  It's just for informational purpose and it doesn't influence the intents processing.
- #96 `NavStackEntry` is no longer nullable in `composableDestination` lambda
- Improved error messages when reading NavStackEntry arguments
- Added support for Long, Float, Double and Boolean navigation arguments
- updated AGP to 7.2.2, updated other dependencies

# 0.2.0

### Fixes

- [#26](https://github.com/AdamKobus/compose-navigation/issues/26) `NavDestinationManager.currentDestination` change to the first visible
  screen happens instantly on app launch now.
  
### Changes

- Updated dependencies
- `NavActionVerifier` now returns VerifyResult enum instead of Boolean
- Improved the current destination evaluation code
- Prepared the code for handling nested / multiple NavHosts
- `INavAction` name was changed to `NavAction` and it now has 2 different implementations: `NavigateAction` and `PopAction`
- `INavDestination` now has 4 different implementations: `NavGraph`, `ScreenDestination`, `DialogDestination` and `PopDestination`. 
  This makes it easy to determine which type of action should be performed and provides more info about back stack.
- `NavActionWrapper` is now an abstract class so that there is less boilerplate code in NavActions declarations using sealed classes.
- `NavGraph` is now an abstract class that requires `name` to be passed in constructor
- `NavGraph` now has abstract method `startDestination()`
- `NavGraphBuilder.composableNavigation` no longer accepts `startDestination` as a param.
- `NavComposable` now accepts `vararg observedGraphs: NavGraph`. If any NavGraph is provided, then received actions will be filtered only to
  those with `fromDestination` belonging to provided graph(s)
- Added `NavLogger`
- Added `NavDestinationManager` - it provides information about current destination and back stack queue
- Added `ComposeNavigation` which should be used to configure the library and get access to its state
- Moved documentation to [docs](docs/README.md)
- Names starting with double underscore (i.e. '__myGraph') are now reserved for internal usage. 
  Trying to declare graph, path or param name like this will now result in `ReservedNameError` being thrown. 
  This behaviour can be disabled using `ComposeNavigation.disableRestrictedNamesCheck()`
- `NavActionConsumer` interface name was changed to `NavigationConsumer`
- Removed all `+` operators and changed the name of `to` infix function to `goTo` and `pop`.
  Using `to` was annoying sometimes because Kotlin would try to interpret it as map in some circumstances.
- Added `NavIntent`, `NavIntentResolver` and `ResolveResult`
- `ComposeNavigation` now has `addNavIntentResolvers()` method which you can use to register your own `NavIntentResolver`s 
- Added `TabBarIntentResolver` - an implementation of `NavIntentResolver` which handles many edge cases related to tab bar navigation.
- `NavAction` now has `asResult` method which produces `ResolveResult` - a class that indicates the processing result by `NavIntentResolver`
- All of the infix functions related to building `NavigateAction` and `PopAction` were moved to `NavDestination` class.
- Removed dependency on Hilt. `ComposeNavigation` object is now responsible for providing all of the dependencies.
- Added `NavOptionsBuilder.popUpTo` extensions
- Updated Jetpack compose to `1.2.0-alpha03`
- Updated Accompanist to `0.24.2-alpha`
- `NavGraph` no longer has `navDestination` method. From now on you should use `screenDestination` and `dialogDestination` instead.
- removed `com.adamkobus.compose.navigation.data` package. Classes were moved to `.action`, `.destination` and `.intent` packages.
- `CurrentDestination` class was replaced by `NavState`. Also added `NavStackEntry` class which holds both destination and 
  the arguments with which it was launched.
- `NavGraphBuilder.composableDestination` and `NavGraphBuilder.composableDialog` content lambda
  now accepts `NavStackEntry` instead of `NavBackStackEntry`

# 0.1.0

### Changes

- Initial release
