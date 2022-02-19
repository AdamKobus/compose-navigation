# Jetpack Compose Navigation

Set of utils to help integrating Jetpack Compose with Jetpack's Navigation

##### Please keep in mind that this is still in early stage. If you're willing to use it in your project, then please remember that the API is still not set in stone

I'm doing refactors / changes almost every day currently, in hopes that I'll be able to simplify the API even more.

##### Api will change a lot in near future

See [https://github.com/AdamKobus/compose-navigation/issues/63](https://github.com/AdamKobus/compose-navigation/issues/63)

## Releases

Current releases can be found at [https://github.com/AdamKobus/compose-navigation/releases](https://github.com/AdamKobus/compose-navigation/releases)

It could potentially remove the need to declare `fun NavGraphBuilder.myGraph()` types of extensions and will also make arguments type safe.

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.adamkobus:compose-navigation:0.2.0"
}
```

## Snapshots

You can find current releases at [Sonatype Snapshots Repository]("https://s01.oss.sonatype.org/content/repositories/snapshots/com/adamkobus/compose-navigation/")

```groovy
repositories {
    maven { url "https://s01.oss.sonatype.org/content/repositories/snapshots/" }
}

dependencies {
    implementation "com.adamkobus:compose-navigation:0.2.2-SNAPSHOT"
}
```

## Main features
  
### Producing navigation actions in ViewModel

This library allows you to produce navigation actions from anywhere, including `ViewModel`. 
Just use `ComposeNavigation.getNavigationConsumer.offer(<action or intent>)`

### Blocking navigation methods

`NavigationConsumer` has suspend versions of its `offer` methods. 
Thanks to this, you can block your coroutine until back stack actually changes.

### Navigation actions and graphs definitions that are easy to read

Example graph:
```kotlin
private const val PARAM_IMAGE_ID = "imageId"

object MyGraph : NavGraph("myGraph") {
  
    override fun startDestination() = Home

    val Home = screenDestination("home")
  
    val ImagePreview = Home.next {
        param(PARAM_IMAGE_ID)
    }
  
    val Alert = dialogDestination("alertDialog")
}

// extension which you can later use in your AnimatedNavHost
@ExperimentalAnimationApi
fun NavGraphBuilder.myGraph() { 
    composableNavigation(MyGraph) {
        composableDestination(MyGraph.Home) { HomeScreen() }
        composableDestination(MyGraph.ImagePreview) { entry ->
            ImagePreviewScreen(entry.getInt(PARAM_IMAGE_ID))
        }
        composableDialog(MyGraph.Alert) { HomeAlert() }
    }
}
```

And actions:
```kotlin
// using wrapper:
sealed class FromHome(action: NavAction) : NavActionWrapper(action) {
    class ToImagePreview(imageId: Int) : FromHome(MyGraph.Home goTo MyGraph.ImagePreview arg imageId)
    object ToAlert : FromHome(MyGraph.Home goTo MyGraph.Alert)
}

// or you can declare actions without NavActionWrapper like this:
fun FromHomeToImagePreview(imageId: Int) = MyGraph.Home goTo MyGraph.ImagePreview arg imageId
val FromHomeToAlert = MyGraph.Home goTo MyGraph.Alert

// back navigation action declaration:
val FromImagePreviewNavigateBack = MyGraph.ImagePreview.pop()
```

### Discarding navigation actions

With `NavActionVerifier` you can discard `NavActions` that don't meet you criteria.

```kotlin
object AppNavActionVerifier : NavActionVerifier {
  
    // will reject an action if its origin is different from the current destination
    override fun isNavActionAllowed(navState: NavState, action: NavAction): VerifyResult {
        return if (state.isCurrent(action.fromDestination)) {
            VerifyResult.Allow
        } else {
            VerifyResult.Discard
        }
    }
}
```

### Navigation action abstraction with navigation intents

`NavIntent` and `NavIntentResolver` can be used to:
- abstract the navigation between feature modules to keep them independent
- open a dev menu while keeping no hard references to it anywhere in your production code
- handle complex navigation like bottom tab bar 
  (see [TabBarIntentResolver](composenav/src/main/java/com/adamkobus/compose/navigation/TabBarIntentResolver.kt))

## Links

- [Documentation with examples](docs/README.md)

# License

```text
MIT License

Copyright (c) 2022 Adam Kobus

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
