# Jetpack Compose Navigation

Set of utils to help with integrating Jetpack Compose and Jetpack's Navigation

Right now it depends quite heavily on Hilt / Dagger to work. 
I'm also using jetpack and accompanist from unstable channel, because their features make lots of stuff more convenient.
I'll switch over to stable versions once updates are released.

##### Please keep in mind that this is still in early stage. If you're willing to use it in your project, then please remember that the API is still not set in stone

I'm doing refactors / changes almost every day currently, in hopes that I'll be able to simplify the API even more, so please keep that in mind :)

## Releases

Current releases can be found at [https://github.com/AdamKobus/compose-navigation/releases](https://github.com/AdamKobus/compose-navigation/releases)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.adamkobus:compose-navigation:0.1.0"
}
```

## Snapshots

You can find current releases at [Sonatype Snapshots Repository]("https://s01.oss.sonatype.org/content/repositories/snapshots/com/adamkobus/compose-navigation/")

```groovy
repositories {
    maven { url "https://s01.oss.sonatype.org/content/repositories/snapshots/" }
}

dependencies {
    implementation "com.adamkobus:compose-navigation:0.1.5-SNAPSHOT"
}
```

## Features

- Navigation destinations | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/AppGraph.kt) 

- Navigation actions | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/Actions.kt)
  
- Processing navigation actions | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/Actions.kt)
  
- Producing navigation actions in ViewModel | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/ui/welcome/WelcomeScreenVM.kt)

- NavGraphBuilder extension | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/AppGraph.kt)

- Multi-module project support | [Demo](demo-settings/src/main/java/com/adamkobus/compose/navigation/demo/settings/nav/SettingsGraph.kt)

- Detecting and dismissing duplicate navigation actions | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/AppNavActionVerifier.kt)

- NavAction operator overloads for more convenient actions declaration | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/Actions.kt)

- Tracking current destination | Demo TBD with settings module

- Support for `dialog` | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/AppGraph.kt)

## Links

- [Documentation with examples](docs)

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
