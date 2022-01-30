package com.adamkobus.compose.navigation.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [NavModuleInternalBinds::class])
object NavModule
