package com.adamkobus.compose.navigation.demo.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.R
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent

@Composable
fun SplashScreen() {
    val vm = hiltViewModel<SplashScreenVM>()
    LifecycleAwareComponent(vm)
    DemoAppBackground {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(painterResource(R.drawable.splash_logo), contentDescription = null)
        }
    }
}
