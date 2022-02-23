package com.adamkobus.compose.navigation.demo.ui.di

import com.adamkobus.compose.navigation.demo.ui.overlay.AppBarOverlayProvider
import com.adamkobus.compose.navigation.demo.ui.overlay.OverlayProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
interface UiModule {

    @Binds
    @IntoSet
    fun bindsAppBarOverlayProvider(impl: AppBarOverlayProvider): OverlayProvider
}
