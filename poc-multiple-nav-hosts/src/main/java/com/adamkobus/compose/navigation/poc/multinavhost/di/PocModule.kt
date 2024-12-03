package com.adamkobus.compose.navigation.poc.multinavhost.di

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationConsumer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PocModule {
    @Provides
    fun providesNavigationConsumer(): NavigationConsumer {
        return ComposeNavigation.getNavigationConsumer()
    }
}
