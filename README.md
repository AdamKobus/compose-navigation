# Jetpack Compose Navigation

Those are just my experiments with navigation handling in Jetpack Compose. 
I'll probably publish it to Maven if it will prove to be useful.

## Features

- Navigation destinations | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/Destinations.kt) 

- Navigation actions | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/Actions.kt)
  
- Processing navigation actions | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/AppGraphProcessor.kt)
  
- Producing navigation actions in ViewModel | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/ui/welcome/WelcomeScreenVM.kt)

- NavGraphBuilder extension | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/AppGraph.kt)

- Multi-module project support | [Demo](demo-settings/src/main/java/com/adamkobus/compose/navigation/demo/settings/nav/SettingsGraph.kt)

- Detecting and dismissing duplicate navigation actions | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/AppNavActionVerifier.kt)

- NavAction operator overloads for more convenient actions declaration | [Demo](demo/src/main/java/com/adamkobus/compose/navigation/demo/nav/Actions.kt)

- Tracking current destination | Demo TBD with settings module

- Support for `dialog` | TBD

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
