### [Back to tutorials list](README.md)

# 9. Checking the result of navigation action or intent

We used [NavigationConsumer] quite a lot so far, but we didn't touch on its blocking methods yet. 
Those provide a way to learn the result of the navigation and block the calling coroutine until the back stack actually updates.

One important thing to know is that the action is considered as complete 
regardless if back stack changes to the expected destination or not.
For now at least, the sole event of back stack updating is considered a success.

We will implement navigation inside the view this time without adding a ViewModel:

> `.nav.TutorialNavActions.kt`
```kotlin
object TutorialNavActions {
    // (...)
    val FromImageToDialog = TutorialGraph.Image goTo TutorialGraph.DetailDialog arg 1
}
```

> `.ui.image.ImageScreen.kt`
```kotlin
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.adamkobus.compose.navigation.ComposeNavigation
import kotlinx.coroutines.launch

@Composable
fun ImageScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = "app icon",
            modifier = Modifier.align(Alignment.Center)
        )

        // new code below

        val scope = rememberCoroutineScope()
        val textEntries = remember { mutableStateOf(emptyList<String>()) }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(textEntries.value) { item ->
                Text(text = item)
            }
        }
        Button(
            onClick = {
                scope.launch {
                    val startTime = SystemClock.elapsedRealtime()
                    val result = ComposeNavigation.getNavigationConsumer().offerBlocking(TutorialNavActions.FromImageToDialog)
                    val elapsed = SystemClock.elapsedRealtime() - startTime
                    textEntries.value += "Result $result in ${elapsed}ms"
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Open a dialog")
        }
    }
}
```

And here is the result:

![Checking the result of nav action](assets/09_result.gif)

As you can see, in the case of double click, the invalid action has been rejected almost instantly. 
That's because no actual interaction with `NavHostController` happens in such case.

### [Back to tutorials list](README.md)

<!-- GENERATED SECTION - DON'T ADD ANY TEXT BELOW THIS TAG -->

[NavigationConsumer]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation/-navigation-consumer/index.md
