package com.uxstate.countriespad.navigation


import com.uxstate.overview.presentation.destinations.DetailsScreenDestination
import com.uxstate.overview.presentation.destinations.OverviewScreenDestination
import com.uxstate.overview.presentation.overview_screen.OverviewScreenNavigator
import com.uxstate.util.model.Country

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate

class CoreNavigator(private val navController: NavController) : OverviewScreenNavigator{

    override fun navigateBackToOverviewScreen() {
        navController.navigate(OverviewScreenDestination)
    }

    override fun navigateToDetailsScreen(country: Country) {
        navController.navigate(DetailsScreenDestination(country))
    }

}