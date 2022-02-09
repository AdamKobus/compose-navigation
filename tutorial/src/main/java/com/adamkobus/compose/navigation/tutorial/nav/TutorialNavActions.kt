package com.adamkobus.compose.navigation.tutorial.nav

object TutorialNavActions {
    val FromWelcomeToImage = TutorialGraph.Welcome goTo TutorialGraph.Image
    val FromWelcomeToList = TutorialGraph.Welcome goTo TutorialGraph.List

    fun fromListToDetail(itemId: Int) = TutorialGraph.List goTo TutorialGraph.Detail arg itemId
}
