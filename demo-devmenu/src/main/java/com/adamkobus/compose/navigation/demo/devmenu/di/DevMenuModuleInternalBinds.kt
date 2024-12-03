package com.adamkobus.compose.navigation.demo.devmenu.di

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.demo.devmenu.nav.DevMenuGraphApplier
import com.adamkobus.compose.navigation.demo.devmenu.nav.DevMenuNavIntentResolver
import com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost.DevMenuTabHostIntentResolver
import com.adamkobus.compose.navigation.demo.ui.nav.NavGraphApplier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
internal interface DevMenuModuleInternalBinds {
    @Binds
    @IntoSet
    fun bindsDevMenuNavIntentResolver(impl: DevMenuNavIntentResolver): NavIntentResolver

    @Binds
    @IntoSet
    fun bindsDevMenuGraphApplier(impl: DevMenuGraphApplier): NavGraphApplier

    @Binds
    @IntoSet
    fun bindsDevMenuTabHostIntentResolver(impl: DevMenuTabHostIntentResolver): NavIntentResolver
}
