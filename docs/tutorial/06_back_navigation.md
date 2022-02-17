### [Back to tutorials list](README.md)

# 6. Using back navigation

In this example we will add a back button to the DetailScreen and perform back navigation using it. 
All we have to do in order to achieve that is adding a button and invoking `.pop()` method 
on `Detail` destination when the button is clicked:

> `.ui.detailscreen.DetailScreen.kt`
```kotlin
// added imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun DetailScreen(itemId: Int) {
    val vm: DetailScreenVM = hiltViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = vm.interactions.onBackClicked) { // Added
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
            navigationConsumer.offer(TutorialGraph.Detail.pop())
        }
    )
}

data class DetailScreenInteractions(
    val onBackClicked: () -> Unit
)
```

What `TutorialGraph.Detail.pop()` did is it created a special type of [NavAction] called [PopAction]. 
In this example, [PopAction] will have `fromDestination` set to `TutorialGraph.Detail`. 
Thanks to this, the action verifier we [added earlier](04_nav_verifier.md) will be able to check if the pop action 
originates from proper destination and prevent it from executing if it doesn't. 
Thanks to this, even if you click the button twice, only DetailScreen will be removed from the back stack.

### Next: [7. Displaying a dialog](07_displaying_dialog.md)

### [Back to tutorials list](README.md)

<!-- GENERATED SECTION - DON'T ADD ANY TEXT BELOW THIS TAG -->

[NavAction]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation.action/-nav-action/index.md
[PopAction]: ../../docs/components/composenav/composenav/com.adamkobus.compose.navigation.action/-pop-action/index.md
