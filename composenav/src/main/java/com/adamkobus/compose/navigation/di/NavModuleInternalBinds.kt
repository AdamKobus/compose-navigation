package com.adamkobus.compose.navigation.di

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.model.NavigationConsumerImpl
import com.adamkobus.compose.navigation.model.StubActionVerifier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
internal interface NavModuleInternalBinds {

    @Binds
    fun bindsNavActionConsumer(impl: NavigationConsumerImpl): NavigationConsumer

    @Binds
    @IntoSet
    fun bindsStubActionVerifier(impl: StubActionVerifier): NavActionVerifier
}
