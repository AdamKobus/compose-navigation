package com.adamkobus.compose.navigation.di

import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.model.NavigationConsumerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface NavModuleInternalBinds {

    @Binds
    fun bindsNavActionConsumer(impl: NavigationConsumerImpl): NavigationConsumer
}
