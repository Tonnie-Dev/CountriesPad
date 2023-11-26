package com.uxstate.countriespad.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency


@OptIn( ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@ExperimentalAnimationApi
@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    //pass your enter/exit transitions here

    val navHostEngine = rememberAnimatedNavHostEngine(
            rootDefaultAnimations = RootNavGraphDefaultAnimations(
                    enterTransition = {
                        defaultDiaryEnterTransition(initialState, targetState)

                    },
                    exitTransition = {
                        defaultDiaryExitTransition(
                                initialState,
                                targetState
                        )
                    },
                    popEnterTransition = { defaultDiaryPopEnterTransition() },
                    popExitTransition = { defaultDiaryPopExitTransition() },
            )
    )

    DestinationsNavHost(
            engine = navHostEngine,
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = modifier,
            dependenciesContainerBuilder = {
                dependency(currentNavigator())
            }
    )
}