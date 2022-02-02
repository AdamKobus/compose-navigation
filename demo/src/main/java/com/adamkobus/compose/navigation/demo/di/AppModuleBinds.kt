package com.adamkobus.compose.navigation.demo.di

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.demo.nav.AppNavActionVerifier
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
}
