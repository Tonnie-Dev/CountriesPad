package com.uxstate.stats

import com.uxstate.util.CountryOrderFormat
import com.uxstate.util.OrderType

sealed class StatsScreenEvent{

    data object AreaButtonToggle:StatsScreenEvent()
    data object PopulationButtonToggle:StatsScreenEvent()
    data object OnSort: StatsScreenEvent()
}
