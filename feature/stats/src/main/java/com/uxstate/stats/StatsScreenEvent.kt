package com.uxstate.stats

import com.uxstate.util.CountryOrderFormat
import com.uxstate.util.OrderType

sealed class StatsScreenEvent{

    data object AreaButtonToggle:StatsScreenEvent()
    data object PopulationButtonToggle:StatsScreenEvent()
    data object OrderToggle:StatsScreenEvent()
    data class OnChangeOrder(val countryOrderFormat: CountryOrderFormat) :
        StatsScreenEvent()
    data class OnSort(val sortType:OrderType): StatsScreenEvent()
}
