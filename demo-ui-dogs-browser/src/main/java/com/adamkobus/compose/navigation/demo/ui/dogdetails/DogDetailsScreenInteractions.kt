package com.adamkobus.compose.navigation.demo.ui.dogdetails

data class DogDetailsScreenInteractions(
    val onOpenDialogClicked: () -> Unit = {},
    val onOpenGalleryClicked: () -> Unit = {},
)
