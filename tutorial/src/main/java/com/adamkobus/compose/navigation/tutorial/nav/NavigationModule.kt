package com.adamkobus.compose.navigation.tutorial.nav

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationConsumer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object NavigationModule {
    @Provides
    fun providesNavigationConsumer(): NavigationConsumer = ComposeNavigation.getNavigationConsumer()
}
