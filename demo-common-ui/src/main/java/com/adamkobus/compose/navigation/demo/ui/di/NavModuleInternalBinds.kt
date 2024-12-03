package com.adamkobus.compose.navigation.demo.ui.di

import com.adamkobus.compose.navigation.demo.ui.nav.NavGraphApplier
import com.adamkobus.compose.navigation.demo.ui.nav.StubNavGraphApplier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
internal interface NavModuleInternalBinds {
    @Binds
    @IntoSet
    fun providesStubNavGraphApplier(impl: StubNavGraphApplier): NavGraphApplier
}
