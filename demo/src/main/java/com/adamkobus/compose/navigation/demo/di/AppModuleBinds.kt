package com.adamkobus.compose.navigation.demo.di

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.demo.nav.AppNavActionVerifier
import com.adamkobus.compose.navigation.demo.nav.tabhost.DemoTabBarIntentResolver
import com.adamkobus.compose.navigation.demo.ui.DevMenuLauncherOverlayProvider
import com.adamkobus.compose.navigation.demo.ui.overlay.OverlayProvider
import com.adamkobus.compose.navigation.demo.ui.tabhost.DemoTabsNavigationOverlayProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
interface AppModuleBinds {

    @Binds
    @IntoSet
    fun bindsAppNavActionVerifier(impl: AppNavActionVerifier): NavActionVerifier

    @Binds
    @IntoSet
    fun bindsDemoTabsNavigationOverlayProvider(impl: DemoTabsNavigationOverlayProvider): OverlayProvider

    @Binds
    @IntoSet
    fun bindsDemoTabBarIntentResolver(impl: DemoTabBarIntentResolver): NavIntentResolver

    @Binds
    @IntoSet
    fun bindsDevMenuLauncherOverlayProvider(impl: DevMenuLauncherOverlayProvider): OverlayProvider
}
