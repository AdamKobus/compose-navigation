package com.adamkobus.compose.navigation.demo.ui.catslist

import androidx.compose.runtime.State
import com.adamkobus.compose.navigation.democore.data.CatInfo

data class CatsListScreenState(
    val isLoading: State<Boolean>,
    val catsList: State<List<CatInfo>>,
)
