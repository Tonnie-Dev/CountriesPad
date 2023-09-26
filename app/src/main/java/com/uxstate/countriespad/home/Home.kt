package com.uxstate.countriespad.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.uxstate.countriespad.navigation.AppNavigation
import com.uxstate.countriespad.navigation.NavGraphs

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun Home() {

    val navController = rememberAnimatedNavController()
    val config = LocalConfiguration.current

    val useBottomNavigation by remember {
        derivedStateOf { config.smallestScreenWidthDp < 600 }
    }

    Scaffold(bottomBar = {
        if (useBottomNavigation) {
            val currentSelectedItem by navController.currentScreenAsState()
            BottomNavigationBar(
                    selectedNavigation = currentSelectedItem,
                    onNavigationSelected = { selected ->
                        navController.navigate(selected, fun NavOptionsBuilder.() {
                            launchSingleTop = true
                            restoreState = true

                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        })
                    },
                    modifier = Modifier.fillMaxWidth()
            )
        } else {


            Spacer(
                    Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars)
            )
        }


    }) { paddingValues ->

        AppNavigation(navController = navController, modifier = Modifier.padding(paddingValues))

    }

}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<NavGraphSpec> {
    val selectedItem = remember { mutableStateOf(NavGraphs.overview) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->


            selectedItem.value = destination.navGraph()
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}

fun NavDestination.navGraph(): NavGraphSpec {
    hierarchy.forEach { destination ->
        NavGraphs.root.nestedNavGraphs.forEach { navGraph ->
            if (destination.route == navGraph.route) {
                return navGraph
            }
        }
    }

    throw RuntimeException("Unknown nav graph for destination $route")
}