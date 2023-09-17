package com.uxstate.countriespad.navigation

import androidx.navigation.NavController
//import com.uxstate.overview.destinations.OverviewScreenDestination
import com.uxstate.details.DetailsScreenNavigator
import com.uxstate.overview.presentation.OverviewScreenNavigator


class CoreNavigator(private val navController: NavController) : OverviewScreenNavigator,DetailsScreenNavigator {
    override fun navigateBackToOverviewScreen() {
//navController.navigate(OverviewScreenDestination)
    }

    override fun navigateToValidator() {
     //   navController.navigate(ValidatorScreenDestination)
    }

    override fun navigateToDetailsScreen() {
        //navController.navigate(DetailsScreenDestination)
    }


}