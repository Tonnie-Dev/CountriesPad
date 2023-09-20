package com.uxstate.countriespad.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.uxstate.overview.presentation.home_screen.OverviewScreenNavigator
import com.uxstate.util.model.Country
import com.uxstate.validator.destinations.ValidatorScreenDestination


class CoreNavigator(private val navController: NavController) : OverviewScreenNavigator{
    override fun navigateBackToOverviewScreen() {
//navController.navigate(OverviewScreenDestination)


    }



    override fun navigateToValidator() {
        navController.navigate(ValidatorScreenDestination)


    }



    override fun navigateToDetailsScreen(country: Country) {
        //navController.navigate(DetailsScreenDestination(country))
    }

    override fun navigateToValidatorScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToStatsScreen() {
        TODO("Not yet implemented")
    }


}