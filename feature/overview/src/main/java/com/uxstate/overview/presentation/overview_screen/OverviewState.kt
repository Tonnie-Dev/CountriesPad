package com.uxstate.overview.presentation.overview_screen

import com.uxstate.util.CountryOrderFormat

data class OverviewState(
    val query: String = "",
    val countriesData: List<com.uxstate.util.model.Country> = emptyList(),
    val isLoading: Boolean = false,
    val isActive:Boolean = false,
    val errorMessage: String = "",
    val isOrderPaneVisible: Boolean = false,
    val navSelectedIndex:Int = 0,
    val countryOrderFormat: CountryOrderFormat = CountryOrderFormat.ByName(
            com.uxstate.util.OrderType.Ascending)
)