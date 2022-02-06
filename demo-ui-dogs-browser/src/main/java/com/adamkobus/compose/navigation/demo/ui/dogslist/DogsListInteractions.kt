package com.adamkobus.compose.navigation.demo.ui.dogslist

import com.adamkobus.compose.navigation.democore.data.DogInfo

data class DogsListInteractions(
    val onDogsItemSelected: (DogInfo) -> Unit = {}
)
