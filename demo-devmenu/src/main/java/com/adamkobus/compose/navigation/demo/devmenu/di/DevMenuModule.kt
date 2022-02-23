package com.adamkobus.compose.navigation.demo.devmenu.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [DevMenuModuleInternalBinds::class])
object DevMenuModule
