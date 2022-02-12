### [Back to tutorials list](README.md)

# 5. Launching new destination with arguments

In this step we will open a detail view from the `ListScreen` and pass the id of the clicked item as a launch argument 
to the opened detail screen.

Let's start by creating a new screen in `.ui.detailscreen` package:

> `.ui.detailscreen.DetailScreen.kt`:
```kotlin
@Composable
fun DetailScreen(itemId: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Opened item with id $itemId", modifier = Modifier.align(Alignment.Center))
    }
}
```

Now we need to add a special destination for this screen to `TutorialGraph`. You have 2 options here:
1. using `next` on already declared destination to build on top of it. 
   This is helpful if you want to avoid repeating same common parts of the paths.
2. creating a destination from scratch

> `.nav.TutorialGraph.kt`
```kotlin
object TutorialGraph : NavGraph("tutorialGraph") {
    const val PARAM_ITEM_ID = "itemId"

    // (...)

    val List = navDestination("list")

    // Option 1: will create "list/{itemId}" destination
    val Detail = List.next { 
        param(PARAM_ITEM_ID)
    }

    // or
    
    // Option 2: will create "detail/{itemId}" destination
    val Detail = navDestination("detail") {
        param(PARAM_ITEM_ID)
    }
}

// I recommend adding extensions like this for more convenient access to param's value
fun NavBackStackEntry.getItemId() = getInt(TutorialGraph.PARAM_ITEM_ID)

@ExperimentalAnimationApi
fun NavGraphBuilder.tutorialGraph() {
    composableNavigation(TutorialGraph) {
       // (...)
       
        composableDestination(TutorialGraph.Detail) { navBackStackEntry ->
            DetailScreen(itemId = navBackStackEntry.getItemId()) // getItemId() is the extension we declared above
        }
    }
}
```

Now all that's left is defining navigation action in `TutorialNavActions` and handling click in the `ListScreen`:

> `.nav.TutorialNavActions.kt`
```kotlin
object TutorialNavActions {
    // (...)
    fun fromListToDetail(itemId: Int) = TutorialGraph.List goTo TutorialGraph.Detail arg itemId
}
```

> `.ui.listscreen.ListScreen.kt`
```kotlin
@HiltViewModel
class ListScreenVM @Inject constructor(
    val navigationConsumer: NavigationConsumer // Added
) : ViewModel() {
    val listContent = mutableStateOf(
        // will generate 50 list elements numbered from 1 to 50
        generateSequence(1) { it + 1 }.take(50).map { ListItemData(it, "List item #$it") }.toList()
    )
    val interactions = ListScreenInteractions(
        onItemSelected = {
            navigationConsumer.offer(TutorialNavActions.fromListToDetail(it.id)) // changed, previously it was TODO
        }
    )
}

```

And that's all. Clicking an element in the list should now open a new screen that displays the id of the list item that you clicked.
Also notice that thanks to the verifier which we implemented in the [previous step](04_nav_verifier.md) it's not possible 
to open multiple detail screens by pressing and releasing multiple list elements at once. 

### Next: [6. Using back navigation](06_back_navigation.md)

### [Back to tutorials list](README.md)

<!-- GENERATED SECTION - DON'T ADD ANY TEXT BELOW THIS TAG -->

