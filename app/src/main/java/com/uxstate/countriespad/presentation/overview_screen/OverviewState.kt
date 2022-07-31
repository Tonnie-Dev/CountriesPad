package com.uxstate.countriespad.presentation.overview_screen

import com.uxstate.countriespad.domain.model.Country

data class OverviewState(
    val query: String = "",
    val countriesData: List<Country> = emptyList(),
    val isLoading: Boolean = false
)
