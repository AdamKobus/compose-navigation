package com.adamkobus.compose.navigation.demo.ui.catdetails

import com.adamkobus.compose.navigation.democore.data.CatInfo

sealed class CatDetailsState {
    object Loading : CatDetailsState()
    class Loaded(val catInfo: CatInfo) : CatDetailsState()
    object Error : CatDetailsState()
}
