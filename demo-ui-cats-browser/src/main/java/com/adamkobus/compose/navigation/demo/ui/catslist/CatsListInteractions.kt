package com.adamkobus.compose.navigation.demo.ui.catslist

import com.adamkobus.compose.navigation.democore.data.CatInfo

data class CatsListInteractions(
    val onCatListItemSelected: (CatInfo) -> Unit = {},
)
