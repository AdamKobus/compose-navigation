package com.adamkobus.compose.navigation.demo.ui.catdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.cats.R
import com.adamkobus.compose.navigation.demo.ui.loading.LoadingScreen
import com.adamkobus.compose.navigation.demo.ui.nav.CatsBrowserGraph
import com.adamkobus.compose.navigation.democore.data.CatInfo
import com.adamkobus.compose.navigation.democore.util.AsyncData
import com.adamkobus.compose.navigation.ui.LocalNavDestination

@Composable
fun CatDetailsScreen(catId: Int) {
    val vm = hiltViewModel<CatDetailsScreenVM>()
    vm.catIdParam.bind(catId)
    LifecycleAwareComponent(observer = vm)
    CatDetailsScreenContent(vm.screenState)
}

@Composable
private fun CatDetailsScreenContent(screenState: CatDetailsScreenState) {
    DemoAppBackground(usesTopBar = true) {
        when (val catInfo = screenState.catInfo.value) {
            is AsyncData.Loading -> LoadingScreen()
            is AsyncData.Present -> CatDetails(catInfo.data)
            is AsyncData.Missing -> CatError()
        }
    }
}

@Composable
private fun CatDetails(catInfo: CatInfo) {
    Column(modifier = Modifier.padding(Paddings.Screen)) {
        Text(text = catInfo.name, style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.height(24.dp))
        val currentDestination = LocalNavDestination.current
        val isCatDetails = currentDestination == CatsBrowserGraph.CatDetails
        Text(
            text = "Current destination: $currentDestination\n" +
                "isCatDetailsDestination: $isCatDetails"
        )
    }
}

@Composable
private fun CatError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Paddings.Screen)
    ) {
        Text(text = stringResource(id = R.string.cat_details_error), style = MaterialTheme.typography.h2, color = Color.Red)
    }
}
