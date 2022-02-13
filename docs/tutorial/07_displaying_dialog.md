### [Back to tutorials list](README.md)

# 7. Displaying a dialog

First let's define a dialog in `DetailScreen.kt`:

> `.ui.detailscreen.DetailScreen.kt`
```kotlin
@Composable
fun DetailScreenDialog(itemId: Int) {
    Card {
        Text(text = "Item id: $itemId", modifier = Modifier.padding(24.dp))
    }
}
```

Then we need to add a destination to `TutorialGraph`:
> `.nav.TutorialGraph.kt`
```kotlin
object TutorialGraph : NavGraph("tutorialGraph") {
    // (...)
    val DetailDialog = Detail.next { 
        path("dialog")
    }.asDialog() // 1
}

@ExperimentalAnimationApi
fun NavGraphBuilder.tutorialGraph() {
    composableNavigation(TutorialGraph) {
        // (...)
        composableDialog(TutorialGraph.DetailDialog) { navBackStackEntry ->
            DetailScreenDialog(itemId = navBackStackEntry.getItemId())
        }
    }
}
```

1. [ScreenDestination.asDialog()] will convert this destination into [DialogDestination] that can be used with `NavGraphBuilder.composableDialog`.

With destination in place, we can define new navigation action:
> `.nav.TutorialNavActions.kt`
```kotlin
object TutorialNavActions {
    // (...)
    fun fromDetailToDialog(itemId: Int) = TutorialGraph.Detail goTo TutorialGraph.DetailDialog arg itemId
}
```

And now the only thing that's left is updating `DetailScreen` by adding new button and handling it by using the action we just defined:
> `.ui.DetailScreen.kt`
```kotlin
@Composable
fun DetailScreen(itemId: Int) {
    val vm: DetailScreenVM = hiltViewModel()
    Box {
        // (...)
        Button(
            onClick = { vm.interactions.onOpenDialogClicked(itemId) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Open a dialog")
        }
    }
}

@HiltViewModel
class DetailScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer
) : ViewModel() {
    val interactions = DetailScreenInteractions(
        // (...)
        onOpenDialogClicked = { itemId ->
            navigationConsumer.offer(TutorialNavActions.fromDetailToDialog(itemId))
        }
    )
}

data class DetailScreenInteractions(
    // (...)
    val onOpenDialogClicked: (Int) -> Unit
)
```

At this point you should be able to launch a dialog from the `DetailScreen`

### [Back to tutorials list](README.md)

<!-- GENERATED SECTION - DON'T ADD ANY TEXT BELOW THIS TAG -->

[DialogDestination]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation.destination/-dialog-destination/index.md
[ScreenDestination.asDialog()]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation.destination/-screen-destination/index.md
