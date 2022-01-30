package com.adamkobus.compose.navigation.ext

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.getString(param: String): String =
    arguments?.getString(param) ?: throw IllegalStateException("Param $param is missing")

fun NavBackStackEntry.getInt(param: String): Int =
    getString(param).toInt()
