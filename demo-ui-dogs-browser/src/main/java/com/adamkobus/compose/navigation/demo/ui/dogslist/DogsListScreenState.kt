package com.adamkobus.compose.navigation.demo.ui.dogslist

import androidx.compose.runtime.State
import com.adamkobus.compose.navigation.democore.data.DogInfo

data class DogsListScreenState(
    val isLoading: State<Boolean>,
    val dogsList: State<List<DogInfo>>,
)
