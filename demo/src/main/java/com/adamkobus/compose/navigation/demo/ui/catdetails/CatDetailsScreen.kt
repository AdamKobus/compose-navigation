package com.adamkobus.compose.navigation.demo.ui.catdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.R
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.loading.LoadingScreen
import com.adamkobus.compose.navigation.democore.data.CatInfo

@Composable
fun CatDetailsScreen(catId: Int) {
    val vm = hiltViewModel<CatDetailsScreenVM>()
    vm.bindCatId(catId)
    LifecycleAwareComponent(observer = vm)
    val screenState = vm.screenState.value
    CatDetailsScreenContent(screenState)
}

@Composable
fun CatDetailsScreenContent(screenState: CatDetailsState) {
    DemoAppBackground(usesTopBar = true) {
        when (screenState) {
            is CatDetailsState.Loading -> LoadingScreen()
            is CatDetailsState.Loaded -> CatDetails(screenState.catInfo)
            is CatDetailsState.Error -> CatError()
        }
    }
}

@Composable
private fun CatDetails(catInfo: CatInfo) {
    Box(modifier = Modifier.padding(Paddings.Screen)) {
        Text(text = catInfo.name, style = MaterialTheme.typography.h2)
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
