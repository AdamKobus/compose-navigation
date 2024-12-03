package com.adamkobus.compose.navigation.demo.ui.dogdetails

import androidx.compose.runtime.State
import com.adamkobus.compose.navigation.democore.data.DogInfo
import com.adamkobus.compose.navigation.democore.util.AsyncData

data class DogDetailsScreenState(
    val dogInfo: State<AsyncData<DogInfo, Throwable>>,
)
