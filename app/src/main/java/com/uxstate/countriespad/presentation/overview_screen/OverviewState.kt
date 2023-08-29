package com.uxstate.countriespad.presentation.overview_screen

import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.countriespad.util.OrderType

data class OverviewState(
    val query: String = "",
    val countriesData: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val isActive:Boolean = false,
    val errorMessage: String = "",
    val isOrderPaneVisible: Boolean = false,
    val countryOrderFormat: CountryOrderFormat = CountryOrderFormat.ByName(OrderType.Ascending)
)
