package com.adamkobus.compose.navigation.demo.ui.dogdetails

import com.adamkobus.compose.navigation.democore.data.DogInfo

sealed class DogDetailsState {
    object Loading : DogDetailsState()
    class Loaded(val dogInfo: DogInfo) : DogDetailsState()
    object Error : DogDetailsState()
}
