package com.uxstate.countriespad.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.uxstate.overview.presentation.details_screen.DetailsScreenNavigator
import com.uxstate.details.destinations.DetailsScreenDestination
import com.uxstate.overview.presentation.home_screen.OverviewScreenNavigator
import com.uxstate.overview.presentation.destinations.OverviewScreenDestination
import com.uxstate.util.model.Country
import com.uxstate.validator.destinations.ValidatorScreenDestination


class CoreNavigator(private val navController: NavController) : OverviewScreenNavigator,
    DetailsScreenNavigator {
    override fun navigateBackToOverviewScreen() {
navController.navigate(OverviewScreenDestination)


    }



    override fun navigateToValidator() {
        navController.navigate(ValidatorScreenDestination)


    }



    override fun navigateToDetailsScreen(country: Country) {
        navController.navigate(DetailsScreenDestination(country))
    }

    override fun navigateToValidatorScreen() {
        TODO("Not yet implemented")
    }


}