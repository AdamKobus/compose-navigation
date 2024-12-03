### [Back to tutorials list](README.md)

# 2. Displaying your first screen with [NavGraph]

We will start by creating a `WelcomeScreen` composable and its `ViewModel` in `.ui.welcome` package:

> `.ui.welcome.WelcomeScreen.kt`
```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun WelcomeScreen() {
    val vm: WelcomeScreenVM = hiltViewModel()
    WelcomeScreenContent(interactions = vm.interactions)
}

@Composable
private fun WelcomeScreenContent(interactions: WelcomeScreenInteractions = WelcomeScreenInteractions.STUB) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = interactions.onShowImageClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Show me an image!")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = interactions.onShowListClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Show me a list!")
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreenContent()
}

@HiltViewModel
class WelcomeScreenVM @Inject constructor() : ViewModel() {
    val interactions = WelcomeScreenInteractions(
        onShowImageClicked = {
            // TODO
        },
        onShowListClicked = {
            // TODO
        }
    )
}

data class WelcomeScreenInteractions(
    val onShowImageClicked: () -> Unit,
    val onShowListClicked: () -> Unit
) {
    companion object {
        val STUB = WelcomeScreenInteractions(
            onShowImageClicked = {},
            onShowListClicked = {}
        )
    }
}
```

`WelcomeScreenPreview` should render two buttons stacked one above the other if all went well. 
We will use those buttons later for navigation.

For now we will focus on displaying the screen we just created. 
To do that, create `.nav` package and put `TutorialGraph.kt` in it with following content:

> `.nav.TutorialGraph.kt`
```kotlin
import com.adamkobus.compose.navigation.destination.NavGraph

object TutorialGraph : NavGraph("tutorialGraph") { // 1
    override fun startDestination() = Welcome // 2

    val Welcome = screenDestination("welcome") // 3
}
```

1. Each graph must have its name. Keep those unique across your project, as it acts as an ID of a graph.
2. Graphs must have starting points. It can be either a destination or another graph
3. We declared a new destination in the app. Ideally, every screen in your app should have its own destination declared like this

To use those destinations, we must first tell Jetpack's Navigation how to render them.
We could do that directly in `NavHost`, but I recommend doing this through an extension, as it makes the `NavHost` less cluttered.

For the graph above, the extension would look like this:

> `nav.TutorialGraph.kt`
```kotlin
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

(...)

@ExperimentalAnimationApi // 1
fun NavGraphBuilder.tutorialGraph() {
    composableNavigation(TutorialGraph) { // 2
        composableDestination(TutorialGraph.Welcome) { WelcomeScreen() }
    }
}
```

1. `@ExperimentalAnimationApi` is required because Compose Navigation is using [Accompanist Navigation Animation]
2. `composableNavigation` is a function from Compose Navigation library and it's used to define a graph inside `NavGraphBuilder`
3. `composableDestination` is another function from Compose Navigation. It declares new destination in `NavGraphBuilder`

And now we can put it all together in `MainActivity`:

> `.MainActivity.kt`
```kotlin
import androidx.compose.animation.ExperimentalAnimationApi
import com.adamkobus.compose.navigation.ComposeNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         YourTheme {
            val controller = rememberAnimatedNavController() // 1
            ComposeNavHost( // 2
               startGraph = TutorialGraph, // 3
               controller = controller,
               navigationId = NavigationId.DEFAULT, // 4
               modifier = Modifier.fillMaxSize()
            ) {
               tutorialGraph() // 5
            }
         }
      }
   }
}
```
1. `rememberAnimatedNavController()` is part of [Accompanist Navigation Animation]
2. [ComposeNavHost] is a Composable from Compose Navigation Library. It acts as a wrapper 
   for AnimatedNavHost from [Accompanist Navigation Animation]. It takes care of configuring [NavComposable] inside it.
3. startGraph determines where will the user land at the application launch. 
4. [NavigationId] is used to identify `NavHostController`s and `ComposeNavHost`s across your application.
   If you're using only single controller in your app, then feel free to provide [NavigationId.DEFAULT] here.
5. We're using the extension we wrote earlier to build navigation graph

Now just launch the app and marvel at the beauty of what we created:

![First screen](assets/02_displaying_first_screen.png)

### Next: [3. Navigation basics using NavAction](03_navigation_basics.md)

### [Back to tutorials list](README.md)

[Accompanist Navigation Animation]: https://google.github.io/accompanist/navigation-animation/

<!-- GENERATED SECTION - DON'T ADD ANY TEXT BELOW THIS TAG -->

[NavGraph]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation.destination/-nav-graph/index.md
[NavigationId]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation/-navigation-id/index.md
[NavigationId.DEFAULT]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation/-navigation-id/index.md
