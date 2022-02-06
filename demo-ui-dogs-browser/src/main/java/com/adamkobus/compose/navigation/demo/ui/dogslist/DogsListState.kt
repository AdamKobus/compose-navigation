package com.adamkobus.compose.navigation.demo.ui.dogslist

import com.adamkobus.compose.navigation.democore.data.DogInfo

sealed class DogsListState {
    object Loading : DogsListState()
    class Loaded(val data: List<DogInfo>) : DogsListState()
}
