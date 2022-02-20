package com.adamkobus.compose.navigation.demo.ui.catdetails

import androidx.compose.runtime.State
import com.adamkobus.compose.navigation.democore.data.CatInfo
import com.adamkobus.compose.navigation.democore.util.AsyncData

data class CatDetailsScreenState(
    val catInfo: State<AsyncData<CatInfo, Throwable>>
)
