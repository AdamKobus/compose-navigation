package com.adamkobus.compose.navigation.tutorial.nav

object TutorialNavActions {
    val FromWelcomeToImage = TutorialGraph.Welcome goTo TutorialGraph.Image
    val FromWelcomeToList = TutorialGraph.Welcome goTo TutorialGraph.List

    fun fromListToDetail(itemId: Int) = TutorialGraph.List goTo TutorialGraph.Detail arg itemId

    val FromDetailNavigateBack = TutorialGraph.Detail pop TutorialGraph.Back
    fun fromDetailToDialog(itemId: Int) = TutorialGraph.Detail goTo TutorialGraph.DetailDialog arg itemId

    val FromImageToDialog = TutorialGraph.Image goTo TutorialGraph.DetailDialog arg 1
}
