package com.uxstate.countriespad.presentation.overview_screen

import com.uxstate.util.model.Country
import com.uxstate.util.CountryOrderFormat
import com.uxstate.util.OrderType

data class OverviewState(
    val query: String = "",
    val countriesData: List<com.uxstate.util.model.Country> = emptyList(),
    val isLoading: Boolean = false,
    val isActive:Boolean = false,
    val errorMessage: String = "",
    val isOrderPaneVisible: Boolean = false,
    val countryOrderFormat: com.uxstate.util.CountryOrderFormat = com.uxstate.util.CountryOrderFormat.ByName(
            com.uxstate.util.OrderType.Ascending)
)
