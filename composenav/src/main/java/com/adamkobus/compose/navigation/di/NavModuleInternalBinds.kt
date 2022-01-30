package com.adamkobus.compose.navigation.di

import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.NavActionProcessor
import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.model.BackActionProcessor
import com.adamkobus.compose.navigation.model.NavActionConsumerImpl
import com.adamkobus.compose.navigation.model.StubActionVerifier
import com.adamkobus.compose.navigation.model.StubNavActionProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
internal interface NavModuleInternalBinds {

    @Binds
    fun bindsNavActionConsumer(impl: NavActionConsumerImpl): NavActionConsumer

    @Binds
    @IntoSet
    fun bindsStubActionVerifier(impl: StubActionVerifier): NavActionVerifier

    @Binds
    @IntoSet
    fun bindsStubNavActionProcessor(impl: StubNavActionProcessor): NavActionProcessor

    @Binds
    @IntoSet
    fun bindsBackActionProcessor(impl: BackActionProcessor): NavActionProcessor
}
