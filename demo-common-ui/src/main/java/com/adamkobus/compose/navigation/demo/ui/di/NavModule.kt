package com.adamkobus.compose.navigation.demo.ui.di

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationStateSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [NavModuleInternalBinds::class])
object NavModule {

    @Provides
    fun provideNavigationStateSource(): NavigationStateSource = ComposeNavigation.getNavigationStateSource()
}
