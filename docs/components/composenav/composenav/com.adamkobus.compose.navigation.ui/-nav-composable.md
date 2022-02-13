//[composenav](../../index.md)/[com.adamkobus.compose.navigation.ui](index.md)/[NavComposable](-nav-composable.md)

# NavComposable

[androidJvm]\

@Composable

fun [NavComposable](-nav-composable.md)(navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), vararg observedGraphs: [NavGraph](../com.adamkobus.compose.navigation.destination/-nav-graph/index.md))

This components acts as a bridge between the owner of [navController](-nav-composable.md) and the navigation state model.

## Parameters

androidJvm

| | |
|---|---|
| observedGraphs | If not empty, then only NavActions that originate from the graphs included in this list will be processed. All NavActions will be processed if [observedGraphs](-nav-composable.md) is empty |
