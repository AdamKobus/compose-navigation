### [Back to tutorials list](README.md)

# 6. Using back navigation

In this example we will add a back button to the DetailScreen and perform back navigation using it. It's actually quite similar to using any other `NavAction`. We will start by adding `Back` destination to our graph and defining the action:

> `.nav.TutorialGraph.kt`
```kotlin
object TutorialGraph : NavGraph("tutorialGraph") { 
    (...)
    val Back = popDestination() // 1
}
```

> `.nav.TutorialNavActions.kt`
```kotlin
object TutorialNavActions {
    (...)
    val FromDetailNavigateBack = TutorialGraph.Detail pop TutorialGraph.Back // 2
}
```
1. This is special type of destination. It can be used to build `PopAction`
2. And the actual `PopAction` declaration is here. Action created like this will attempt to declare.

Next, let's update `DetailsScreen`:

> `.ui.detailscreen.DetailScreen.kt`
```kotlin
@Composable
fun DetailScreen(itemId: Int) {
    val vm: DetailScreenVM = hiltViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = vm.interactions.onBackClicked) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
        }
        Text(text = "Displaying item with id $itemId", modifier = Modifier.align(Alignment.Center))
    }
}

@HiltViewModel
class DetailScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer
) : ViewModel() {
    val interactions = DetailScreenInteractions(
        onBackClicked = {
            navigationConsumer.offer(TutorialNavActions.FromDetailNavigateBack)
        }
    )
}

data class DetailScreenInteractions(
    val onBackClicked: () -> Unit
)
```

It might seem like a lot of work to just perform back navigation. 
The payoff is that that the navigation action verifier we [added earlier](04_nav_verifier.md) will be able to check if the pop action 
originates from proper destination and prevent it from executing if it doesn't.

### [Back to tutorials list](README.md)
