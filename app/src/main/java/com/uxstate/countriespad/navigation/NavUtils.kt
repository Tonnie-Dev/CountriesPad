package com.uxstate.countriespad.navigation

import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder

fun DependenciesContainerBuilder<*>.currentNavigator(): CoreNavigator {
    return CoreNavigator(navController = navController)
}
