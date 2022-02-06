# Unreleased changes

### Fixes

### Changes

- Updated dependencies
- `NavActionVerifier` now returns VerifyResult enum instead of Boolean
- Improved the current destination evaluation code
- Prepared the code for handling nested / multiple NavHosts
- `INavAction` now has 2 different implementations: `NavigateAction` and `PopAction`
- `INavDestination` now has 3 different implementations: `NavGraph`, `NavDestination` and `PopDestination`. This makes it easy to determine which type of action should be performed.
- `NavActionWrapper` is now an abstract class so that there is less boilerplate code in NavActions declarations using sealed classes.
- `NavGraph` is now an abstract class that requires `name` to be passed in constructor
- `NavGraph` now has abstract method `startDestination()`
- `NavGraphBuilder.composableNavigation` no longer accepts `startDestination` as a param.
- `NavComposable` now accepts `vararg observedGraphs: NavGraph`. If any NavGraph is provided, then received actions will be filtered only to those with `fromDestination` belonging to provided graph(s)

# 0.1.0

### Changes

- Initial release
