# Declaring navigation graph and destinations

`NavGraph` and `INavDestination` are basic building blocks of the app's navigation graph. `NavGraph` comes with `navDestination` functions that help with  declaring the destinations. Additionally, `INavDestination` comes with `next` method, which allows you to expand on already defined path.

```kotlin
object DemoGraph : NavGraph {
    override val name = "demoGraph"

    const val PARAM_DEMO_ID = "demoId"

    val DemosList = navDestination("demosList") // produces "demoGraph/demosList" route
    val DemosListHelp = navDestination("demosListHelp") // produces "demoGraph/demosListHelp" route
    val DemoDetails = DemosList.next { param(PARAM_DEMO_ID) } // produces "demoGraph/demosList/{demoId}" route
    val LongRoute = navDestination("longRoute") {
        path("part1")
        param("someParam")
        path("part2")
        param("anotherParam")
    } // produces "demoGraph/longRoute/part1/{someParam}/part2/{anotherParam}" route

    val DemoDialog = navDestination("demoDialog")
}
```

One of the destinations in the example above uses a param. You can add an extensions to `NavGraphBuilder` to have easier access to its value:

```kotlin
fun NavBackStackEntry.demoId() = getInt(DemoGraph.PARAM_DEMO_ID )
```

# Building Jetpack's Navigation graph

I recommend declaring a `NavGraphBuilder` extension. It makes using your graph much easier in other modules.
This library also provides few extensions to help with building the graph:

- `composableNavigation` - to declare new graph
- `composableDestination` - to declare new Composable `NavBackStackEntry` provider
- `composableDialog` - to provide Dialog composable

Here is how you could declare an extension using the structures we defined earlier:

```kotlin
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.demoGraph() {
    composableNavigation(
        graph = DemoGraph,
        startDestination = DemoGraph.DemosList 
    ) {
        composableDestination(DemoGraph.DemosList) {
            DemosListScreen() // your @Composable
        }

        composableDestination(DemoGraph.DemosListHelp) {
            DemosListHelpScreen()
        }

        composableDestination(DemoGraph.DemoDetails) { backStackEntry ->
            DemoDetailsScreen(backStackEntry.demoId()) // demoId() is an extension we declared earlier
        }
       
        composableDestination(DemoGraph.LongRoute ) {
            LongRouteScreen()
        }

        composableDialog(DemoGraph.DemoDialog) {
            DemoDialog()
        }
    }
}
```

The `@OptIn(ExperimentalAnimationApi::class)` annotation is required,
because this library uses Accompanist [navigation-animation](https://google.github.io/accompanist/navigation-animation/).
Once the animated navhost becomes part of the Jetpack Compose library, this library will be updated.

# Application set up

Now that we have a graph definition in place, it's time to actually use it in the application. Let's start by declaring the app's NavHost:

```kotlin
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoNavHost(
    modifier: Modifier = Modifier
) {
    val navHostController = rememberAnimatedNavController()
    NavComposable(navController = navHostController) // 1.
    AnimatedNavHost(
        navController = navHostController,
        startDestination = DemoGraph.name, // 2.
        modifier = modifier
    ) {
        demoGraph() // 3.
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoTheme {
                DemoNavHost()
            }
        }
    }
}

```

1. This is a component from this library. Its responsibility is to consume navigation actions. It should be declared in Compose view tree only once.
2. Each application must have its starting point. In this case, we tell the NavHost to start with `DemoGraph`.
3. We are using the `NavGraphBuilder.demoGraph()` extension declared earlier. As you can see, it declutters the `NavHost` definition quite a lot, not to mention it's convenient way to include graphs from other modules in your project as well.

> `NavComposable`'s ViewModel depends on `Hilt` to work properly
>
> Please keep that in mind. I might modify this library in future by providing custom annotations to decouple the dependency management from Dagger / Hilt.
> For now it's not high priority for me, as `Hilt` my default choice for DI.

# Declaring Navigation Actions

So far we declared destinations and configured the app to display the navigtaion graph. Now it's time to set up the actual navigation.

The base structure for doing that is `NavAction`. Treat `NavAction` as en event, that when consumed, should result in the application navigating from one destination to another while providing the defined `params` to the rendered screen.

The bare minimum required to declare proper `NavAction` is:
- `fromDestination` - destination from which `NavAction` originates. This is purely informational and not required for the navigation itself. It can be used by `NavActionVerifier` though.
- `toDestination` - route of this `NavDestination` will be provided to `NavHostController`
- `params` - will be provided to `NavHostController`. Those params will be available in `NavBackStackEntry` later.

Optional:
- `navigateWithController` - lambda, which if not null, will be invoked on `NavHostController` to perform navigation action
- `navigate` - `NavOptionsBuilder` type-safe builder which will be provided to `NavHostController` if it's not null.

If both `navigateWithController` and `navigate` are not null, then only `navigateWithController` will be invoked.

To declare a `NacAction`, you can use its constructor, but there are more convenient ways:

```kotlin
fun FromListToDetail(demoId: Int) = DemoGraph.DemosList to DemoGraph.DemoDetails arg demoId

// alternative:
fun FromListToDetail(demoId: Int) = DemoGraph.DemosList + DemoGraph.DemoDetails + demoId
```

Personally, I prefer declaring NavActions using sealed classes like this:

```kotlin
sealed class FromDemosList(action: NavAction) : NavActionWrapper(action) {
    object ToHelp : FromDemosList(DemoGraph.DemosList to DemoGraph.DemosListHelp navigate {
        launchSingleTop = true
    })

    // as exaplained above, navigate block is optional
    // the default behaviour of NavHostController.navigate should be expected.
    class ToDemoDetails(demoId: Int) : FromDemosList(DemoGraph.DemosList to DemoGraph.DemoDetails arg demoId)
}

sealed class FromDemoDetails(action: NavAction) : NavActionWrapper(action) {
    object ToSomeOtherScreen : FromDemosList(DemoGraph.DemoDetails to DemoGraph.SomeOtherScreen navigate {
        popUpTo(DemoGraph.name)
    })
}

sealed class FromGlobal(action: NavAction) : NavActionWrapper(action) {
    object ToDemoDialog : FromGlobal(GlobalDestination to DemoGraph.DemoDialog)
}
```

I like sealed class way, because it groups the events originating from the same destination better. It also allows for more convenient checks in `NavActionVerifier`. It might be a small gain for the price of boilerplate code created in the process though.

# Navigating

One of the main requirements of this library was to be able to produce navigation actions from within ViewModel.

Reasoning:
- ViewModel should decide what's the view state, that includes currently visible view changes
- NavAction can be a result of changes coming from the Model, which should be completely separated from the View
- the actual navigation might need to happen after an asynchronous task is executed. For example, after the user has pressed `Disconnect` button, we might want to wait for the actual disconnect to happen before navigating. Such operations belong to VM, not view.

This goal was kind of hard to achieve, because `NavHostController` is part of the view. This was resolved by:
1. Introducing navigation state data source `NavigationState` (Model layer)
2. Introducing helper `@Composable` called `NavComposable` which consumes any actions available in `NavigationState` and delivers them to `NavHostController`. It uses `hiltViewModel`, yet another hard dependency on `Hilt`
3. Introducing `NavActionConsumer` - an interface which should be used by the VMs in your application to deliver `NavAction`s to `NavigationState`.

With all that said, the only thing you have to do in your app to emit `NavAction` is:

```kotlin
@HiltViewModel
class DemoListScreenVM @Inject constructor(
    private val navActionConsumer: NavActionConsumer
) : ViewModel() {
    // your interaction callback
    fun onDemoClicked(demo: DemoItem) {
        viewModelScope.launch {
            navActionConsumer.offer(FromDemosList.ToDemoDetails(demo.id))
        }
    }

    fun onHelpClicked() {
        viewModelScope.launch {
            navActionConsumer.offer(FromDemosList.ToHelp)
        }
    }
}
```

# Blocking NavActions

This feature was added as a way to prevent putting multiple instances of the same screen on the back stack, i.e. as a result of double click.

There are many ways to prevent it (i.e. blocking interaction in view, using `launchSingleTop` where applicable, etc.). The advantage of the way used in this library is that it allows you to block all duplicates globally, without having to write checks in your views.

You can achieve this by implementing your own `NavActionVerifier`:
```kotlin
class DemoNavActionVerifier @Inject constructor() : NavActionVerifier {
    override fun isNavActionAllowed(currentDestination: INavDestination, action: NavAction): Boolean =
        when (currentDestination) {
            // will prevent opening another DemoDetails if it's already in foreground
            DemoGraph.DemoDetails -> action.toDestination != AppGraph.DemoDetails 
            else -> true
        }
}

// blocking actions globally if they don't originate from expected destination
class DemoNavActionVerifier @Inject constructor() : NavActionVerifier {
    override fun isNavActionAllowed(currentDestination: INavDestination, action: NavAction): Boolean =
        action.fromDestination == GlobalDestination || currentDestination == action.fromDestination
}
// action.fromDestination == GlobalDestination is needed so that Global actions are not dismissed
```

You must also provide this verifier via Hilt:

```kotlin
@InstallIn(SingletonComponent::class)
@Module
interface DemoModuleBinds {

    @Binds
    @IntoSet
    fun bindsDemoNavActionVerifier (impl: DemoNavActionVerifier): NavActionVerifier
}
```

`@IntoSet` annotation is important. This is how the library collects all of the verifiers. In future I might replace it with dedicated annotation to make it more convenient.

# Displaying Dialog

To display a dialog you don't have to do anything special. You can use `NavDestination` and `NavAction` as usual. Search for `DemoDialog` declarations in examples above.

To dismiss a dialog you can use `NavAction.Back` or declare your own action like this:

```kotlin
sealed class FromDemoDialog(action: NavAction) : NavAction(action) {
    object Dismiss : FromDemoDialog(NavAction(AppGraph.DemoDialog, PopBackStackDestination, navigateWithController = { it.popBackStack() }))
}
```
