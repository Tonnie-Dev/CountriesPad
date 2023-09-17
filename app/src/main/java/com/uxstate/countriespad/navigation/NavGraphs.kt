package com.uxstate.countriespad.navigation

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route
import com.uxstate.countriespad.presentation.destinations.DetailsScreenDestination
import com.uxstate.countriespad.presentation.destinations.OverviewScreenDestination

object NavGraphs {

    //Overview's Module NavGraph - defines NavGraph by instantiating NavGraphSpecs
    val overview = object :NavGraphSpec{

        override val route = "overview"

        override val startRoute = OverviewScreenDestination
        override val destinationsByRoute= listOf<DestinationSpec<*>>(OverviewScreenDestination)
                .associateBy{it.route}

    }

    //Detail's Module NavGraph - defines NavGraph by instantiating NavGraphSpecs
    val details = object : NavGraphSpec {

        override val route = "details"
        override val startRoute = DetailsScreenDestination
        override val destinationsByRoute = listOf<DestinationSpec<*>>(DetailsScreenDestination)
                .associateBy { it.route }


    }

    //Validator's Module NavGraph - defines NavGraph by instantiating NavGraphSpecs

    val validator = object : NavGraphSpec {

        override val route = "details"
        override val startRoute = DetailsScreenDestination
        override val destinationsByRoute = listOf<DestinationSpec<*>>(DetailsScreenDestination)
                .associateBy { it.route }

    }

    //Root NavGraph showing Overview as the starting screen

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = overview
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(overview, details, validator)
    }

}