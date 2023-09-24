package com.uxstate.stats

import com.uxstate.util.CountryOrderFormat
import com.uxstate.util.OrderType
import com.uxstate.util.model.Country

data class StatsScreenState(
    val countryData:List<Country> = emptyList(),
    val isAreaButtonEnabled: Boolean = true,
    val isPopulationButtonEnabled: Boolean =false,
    val errorMessage:String = "",
    val isLoading:Boolean = true,
    val sortOrder:OrderType = OrderType.Ascending,
    val countryOrderFormat: CountryOrderFormat = CountryOrderFormat.ByArea(
            OrderType.Ascending
))
