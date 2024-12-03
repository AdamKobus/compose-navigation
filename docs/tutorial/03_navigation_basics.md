### [Back to tutorials list](README.md)

# 3. Navigation basics using [NavAction]

We displayed a screen with two buttons in the [previous step](02_first_graph.md) of this tutorial. 
In this step we wil actually make those buttons do stuff.

Let's start by declaring two addictional screens:

> `.ui.image.ImageScreen.kt`:
```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ImageScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = "app icon",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
```

> `.ui.list.ListScreen.kt`:
```kotlin
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@Composable
fun ListScreen() {
    val vm: ListScreenVM = hiltViewModel()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(vm.listContent.value, key = { it.id }) { itemData ->
            ListElement(itemData, vm.interactions.onItemSelected)
        }
    }
}

@Composable
private fun ListElement(data: ListItemData, onItemClicked: (ListItemData) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClicked(data) }
    ) {
        Text(text = data.title, modifier = Modifier.padding(14.dp))
    }
}

@HiltViewModel
class ListScreenVM @Inject constructor() : ViewModel() {
    val listContent = mutableStateOf(
        // will generate 50 list elements numbered from 1 to 50
        generateSequence(1) { it + 1 }.take(50).map { ListItemData(it, "List item #$it") }.toList()
    )
    val interactions = ListScreenInteractions(
        onItemSelected = {
            // TODO
        }
    )
}

data class ListScreenInteractions(
    val onItemSelected: (ListItemData) -> Unit
)

data class ListItemData(
    val id: Int,
    val title: String
)
```

Now we need to declare destinations for those 2 screens and update `tutorialGraph()` extension:

> `.nav.TutorialGraph.kt`
```kotlin
object TutorialGraph : NavGraph("tutorialGraph") {
    override fun startDestination() = Welcome

    val Welcome = screenDestination("welcome")
    val Image = screenDestination("image")
    val List = screenDestination("list")
}

@ExperimentalAnimationApi
fun NavGraphBuilder.tutorialGraph() {
    composableNavigation(TutorialGraph) {
        composableDestination(TutorialGraph.Welcome) { WelcomeScreen() }
        composableDestination(TutorialGraph.Image) { ImageScreen() }
        composableDestination(TutorialGraph.List) { ListScreen() }
    }
}
```

With new destinations in place we can start adding navigation actions. Let's define them in `.nav.TutorialNavActions` file like this:

> `.nav.TutorialNavActions.kt`
```kotlin
object TutorialNavActions {
    val FromWelcomeToImage = TutorialGraph.Welcome goTo TutorialGraph.Image
    val FromWelcomeToList = TutorialGraph.Welcome goTo TutorialGraph.List
}
```

The format used above should be quite obvious. If you inspect `FromWelcomeToImage` or `FromWelcomeToList` 
you will see that [INavDestination.goTo(INavDestination)] created new [NavigateAction]. 
We can use it now in `WelcomeScreenVM` to perform the actual navigation:

> `.ui.welcome.WelcomeScreen.kt`
```kotlin
import com.adamkobus.compose.navigation.NavigationConsumer

(...)

@HiltViewModel
class WelcomeScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer // 1.
) : ViewModel() {
    val interactions = WelcomeScreenInteractions(
        onShowImageClicked = {
            viewModelScope.launch {
                navigationConsumer.offer(TutorialNavActions.FromWelcomeToImage)
            }
        },
        onShowListClicked = {
            viewModelScope.launch {
                navigationConsumer.offer(TutorialNavActions.FromWelcomeToList)
            }
        }
    )
}
```

1. This is a util from Compose Navigation library. Its purpose is to queue offered actions and intents for further processing.

Now you should be able to launch `ImageScreen` and `ListScren` by clicking the buttons.

One thing you might've noticed is that double clicking the "Show me an image!" button opens the `ImageScreen` twice. 

This can be prevented using `launchSingleTop = true` option like below:

> `.nav.TutorialNavActions.kt`
```kotlin
object TutorialNavActions {
    val FromWelcomeToImage = TutorialGraph.Welcome goTo TutorialGraph.Image withOptions navActionOptions {
        launchSingleTop = true
    }
    val FromWelcomeToList = TutorialGraph.Welcome goTo TutorialGraph.List withOptions navActionOptions {
        launchSingleTop = true
    }
}
```

Even though it will work, it doesn't solve a different issue. If you hold both buttons and release them at the same time, 
then both `ImageScreen` and `ListScreen` will be put into back stack:

![Broken back stack](assets/03_broken_back_stack.gif)

In the next step we will learn how to fix it.

### Next: [4. Preventing navigation from happening with NavActionVerifier](04_nav_verifier.md)

### [Back to tutorials list](README.md)

<!-- GENERATED SECTION - DON'T ADD ANY TEXT BELOW THIS TAG -->

[NavAction]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation.action/-nav-action/index.md
[NavigateAction]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation.action/-navigate-action/index.md
[INavDestination.goTo(INavDestination)]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md
