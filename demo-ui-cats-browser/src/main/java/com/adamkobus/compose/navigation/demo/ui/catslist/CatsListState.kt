package com.adamkobus.compose.navigation.demo.ui.catslist

import com.adamkobus.compose.navigation.democore.data.CatInfo

sealed class CatsListState {
    object Loading : CatsListState()
    class Loaded(val data: List<CatInfo>) : CatsListState()
}
